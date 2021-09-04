package event

import Bot
import exceptions.InternalErrorException
import listener.*
import utils.readGroupId
import utils.readRawMessage
import utils.readUserId
import kotlin.reflect.KClass

class EventManager(val bot: Bot) {
    val listeners: MutableMap<KClass<*>, MutableList<EventListener<*>>> = mutableMapOf()
    val commandPrefix = mutableSetOf<Char>()
    val commandMap = mutableMapOf<CommandType, MutableMap<String, CommandListener>>()

    enum class CommandType {
        GROUP_COMMAND,
        PRIVATE_COMMAND
    }

    fun setCommandPrefix(vararg prefix: Char): EventManager {
        for (c in prefix) commandPrefix.add(c)
        return this
    }

    inline fun <reified T : Event> register(listener: EventListener<T>): EventManager {
        val eventClass = T::class
        val eventListeners: MutableList<EventListener<*>> = listeners.getOrPut(eventClass) { mutableListOf() }
        eventListeners.add(listener)
        return this
    }

    inline fun <reified T : CommandListener> commandRegister(listener: T): EventManager {
        val map = commandMap.getOrPut(
            when (listener) {
                is PrivateCommandListener<*> -> CommandType.PRIVATE_COMMAND
                is GroupCommandListener<*> -> CommandType.GROUP_COMMAND
                else -> throw InternalErrorException()
            }
        ) { mutableMapOf() }
        for (prefix in listener.commandPrefix()) {
            map[prefix] = listener
        }
        return this
    }

    suspend inline fun <reified T : PassiveEvent> notify(event: T): EventManager {
        listeners[T::class]?.asSequence()
            ?.filterIsInstance<PassiveEventListener<T>>()
            ?.forEach { it.handle(bot, event) }
        return this
    }

    suspend inline fun <reified T : PrivateMsgEvent> notify(event: T, rowEventString: String): EventManager {
        listeners[T::class]?.asSequence()
            ?.filterIsInstance<PrivateMsgEventListener<T>>()
            ?.forEach {
                it.handle(
                    bot,
                    event,
                    bot.utils.getFriendById(rowEventString.readUserId())
                )
            }
        val rowMessage = rowEventString.readRawMessage()
        if (commandMap.contains(CommandType.PRIVATE_COMMAND) && commandPrefix.contains(rowMessage[0])) {
            val com = rowMessage.split(" ")[0]
            if (com.isNotEmpty()) {
                val command = com.substring(1 until com.length)
                val map = commandMap[CommandType.PRIVATE_COMMAND]!!
                if (map.contains(command)) {
                    @Suppress("UNCHECKED_CAST")
                    ((map[command]) as PrivateCommandListener<T>).handle(
                        bot,
                        event,
                        bot.utils.getFriendById(rowEventString.readUserId())
                    )
                }
            }
        }
        return this
    }

    suspend inline fun <reified T : GroupMsgEvent> notify(event: T, rowEventString: String): EventManager {
        val groupId = rowEventString.readGroupId()
        listeners[T::class]?.asSequence()
            ?.filterIsInstance<GroupMsgEventListener<T>>()
            ?.forEach {
                it.handle(
                    bot,
                    event,
                    bot.utils.getGroupUserById(groupId, rowEventString.readUserId()),
                    bot.utils.getGroupById(groupId)
                )
            }

        val rowMessage = rowEventString.readRawMessage()
        if (commandMap.contains(CommandType.GROUP_COMMAND) && commandPrefix.contains(rowMessage[0])) {
            val com = rowMessage.split(" ")[0]
            if (com.isNotEmpty()) {
                val command = com.substring(1 until com.length)
                val map = commandMap[CommandType.GROUP_COMMAND]!!
                if (map.contains(command)) {
                    @Suppress("UNCHECKED_CAST")
                    ((map[command]) as GroupCommandListener<T>).handle(
                        bot,
                        event,
                        bot.utils.getGroupUserById(
                            groupId,
                            rowEventString.readUserId()
                        ),
                        bot.utils.getGroupById(groupId)
                    )
                }
            }
        }
        return this
    }

    suspend inline fun <reified T : GroupEvent> notify(event: T, rowEventString: String): EventManager {
        listeners[T::class]?.asSequence()
            ?.filterIsInstance<GroupEventListener<T>>()
            ?.forEach {
                it.handle(
                    bot,
                    event,
                    bot.utils.getGroupById(rowEventString.readGroupId())
                )
            }
        return this
    }
}