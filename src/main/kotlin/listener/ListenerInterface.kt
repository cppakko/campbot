package listener

import Bot

interface Event
interface Api

interface EventListener<T: Event> {
    fun handle(bot: Bot,info: T)
}

