package listener

import Bot
import entity.Group
import entity.GroupUser
import entity.User

interface Event

interface PrivateMsgEvent : Event
interface GroupEvent : Event
interface GroupMsgEvent : GroupEvent
interface PassiveEvent : Event
interface Api

interface EventListener<T : Event>
interface CommandListener {
    fun commandPrefix(): List<String>
}

interface PassiveEventListener<T: PassiveEvent> : EventListener<T> {
    suspend fun handle(bot: Bot, info: T)
}

interface PrivateMsgEventListener<T : PrivateMsgEvent> : EventListener<T> {
    suspend fun handle(bot: Bot, info: T, user: User)
}

interface GroupMsgEventListener<T : GroupMsgEvent> : EventListener<T> {
    suspend fun handle(bot: Bot, info: T, user: GroupUser, group: Group)
}

interface GroupEventListener<T : GroupEvent> : EventListener<T> {
    suspend fun handle(bot: Bot, info: T, group: Group)
}

interface GroupCommandListener<T : GroupMsgEvent> : CommandListener {
    suspend fun handle(bot: Bot, info: T, user: GroupUser, group: Group)
}

interface PrivateCommandListener<T : PrivateMsgEvent> : CommandListener {
    suspend fun handle(bot: Bot, info: T, user: User)
}

//TODO
interface UniversalCommandListener