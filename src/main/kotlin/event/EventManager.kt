package event

import Bot
import listener.Event
import listener.EventListener
import kotlin.reflect.KClass

class EventManager(val bot: Bot) {
    val listeners: MutableMap<KClass<*>, MutableList<EventListener<*>>> = mutableMapOf()

    inline fun <reified T : Event> register(listener: EventListener<T>) {
        val eventClass = T::class
        val eventListeners: MutableList<EventListener<*>> = listeners.getOrPut(eventClass) { mutableListOf() }
        eventListeners.add(listener)
    }

    inline fun <reified T : Event> notify(event: T) {
        listeners[T::class]?.asSequence()
            ?.filterIsInstance<EventListener<T>>()
            ?.forEach { it.handle(bot,event) }
    }
}