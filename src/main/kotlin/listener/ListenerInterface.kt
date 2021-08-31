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

interface EventListener<T: Event>

interface PassiveEventListener<T: PassiveEvent> : EventListener<T> {
    fun handle(bot: Bot, info: T)
}

interface PrivateMsgEventListener<T: PrivateMsgEvent> : EventListener<T> {
    fun handle(bot: Bot,info: T,user: User)
}

interface GroupMsgEventListener<T: GroupMsgEvent> : EventListener<T> {
    fun handle(bot: Bot, info: T, user: GroupUser, group: Group)
}

interface GroupEventListener<T: GroupEvent> : EventListener<T> {
    fun handle(bot: Bot,info: T,group: Group)
}