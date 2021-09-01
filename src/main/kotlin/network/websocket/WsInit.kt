package network.websocket

import Bot
import event.EventPasser
import io.ktor.client.*
import io.ktor.client.features.websocket.*
import io.ktor.http.*
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import utils.outputMessages

//TODO RENAME
class WsInit {
    companion object {
        val callApiChannel = Channel<Pair<String,Channel<String>>>()
        val apiOutputChannel = Channel<Channel<String>>()
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
                client.webSocket(method = HttpMethod.Post, host = bot.ipAddress, port = bot.port, path = "/api") {
                    launch {
                        for (apiCall in callApiChannel) {
                            this@webSocket.send(apiCall.first)
                            apiOutputChannel.send(apiCall.second)
                        }
                    }
                    //TODO logger
                    launch {
                        try {
                            for (message in incoming) {
                                message as? Frame.Text ?: continue
                                println(message.readText() + " TEST TEST TEST")
                                for (channel in apiOutputChannel) {
                                    channel.send(message.readText())
                                }
                            }
                        } catch (e: Exception) {
                            println("Error while receiving: " + e.localizedMessage)
                        }
                    }.join()
                }
            }
            bot.rootPathClientJob = messageOutputRoutine
        }
    }
}


