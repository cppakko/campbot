package network.websocket

import Bot
import io.ktor.client.*
import io.ktor.client.features.websocket.*
import io.ktor.http.*
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import mu.KotlinLogging
import utils.outputMessages

//TODO RENAME
class WsInit {
    companion object {
        val callApiChannel = Channel<String>()
    }

    suspend fun wsInit(bot: Bot) {
        coroutineScope {
            val client = HttpClient {
                install(WebSockets)
            }
            val messageOutputRoutine = launch {
                client.webSocket(method = HttpMethod.Get, host = bot.ipAddress, port = bot.port, path = "/") {
                    launch { this@webSocket.outputMessages() }.join()
                }
            }
            launch {
                client.webSocket(method = HttpMethod.Get, host = bot.ipAddress, port = bot.port, path = "/api") {
                    launch { this@webSocket.outputMessages() }
                    launch { for (apiCall in callApiChannel) this@webSocket.send(apiCall) }
                }
            }
            bot.rootPathClientJob = messageOutputRoutine
        }
    }
}


