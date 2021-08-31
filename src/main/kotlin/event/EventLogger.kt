package event

import kotlinx.coroutines.channels.Channel
import mu.KotlinLogging

internal class EventLogger {
    companion object {
        private val logger = KotlinLogging.logger {}
        val channel = Channel<String>()
    }

    suspend fun init() {
        for (y in channel) logger.info { y }
    }
}