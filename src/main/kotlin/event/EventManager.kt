package event

import Bot
import listener.*
import utils.readGroupId
import utils.readUserId
import kotlin.reflect.KClass

class EventManager(val bot: Bot) {
    val listeners: MutableMap<KClass<*>, MutableList<EventListener<*>>> = mutableMapOf()

    inline fun <reified T : Event> register(listener: EventListener<T>) {
        val eventClass = T::class
        val eventListeners: MutableList<EventListener<*>> = listeners.getOrPut(eventClass) { mutableListOf() }
        eventListeners.add(listener)
    }

    inline fun <reified T : PassiveEvent> notify(event: T) {
        listeners[T::class]?.asSequence()
            ?.filterIsInstance<PassiveEventListener<T>>()
            ?.forEach { it.handle(bot,event) }
    }

    suspend inline fun <reified T : PrivateMsgEvent> notify(event: T, rowEventString: String) {
        listeners[T::class]?.asSequence()
            ?.filterIsInstance<PrivateMsgEventListener<T>>()
            ?.forEach {
                it.handle(
                    bot,
                    event,
                    bot.utils.getFriendById(rowEventString.readUserId())
                )
            }
    }

    suspend inline fun <reified T : GroupMsgEvent> notify(event: T, rowEventString: String) {
        listeners[T::class]?.asSequence()
            ?.filterIsInstance<GroupMsgEventListener<T>>()
            ?.forEach {
                val groupId = rowEventString.readGroupId()
                it.handle(
                    bot,
                    event,
                    bot.utils.getGroupUserById(groupId, rowEventString.readUserId()),
                    bot.utils.getGroupById(groupId)
                )
            }
    }

    suspend inline fun <reified T : GroupEvent> notify(event: T, rowEventString: String) {
        listeners[T::class]?.asSequence()
            ?.filterIsInstance<GroupEventListener<T>>()
            ?.forEach {
                it.handle(
                    bot,
                    event,
                    bot.utils.getGroupById(rowEventString.readGroupId())
                )
            }
    }
}