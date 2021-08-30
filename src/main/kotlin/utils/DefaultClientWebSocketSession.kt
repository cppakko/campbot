package utils

import event.EventLogger
import event.EventPasser
import io.ktor.client.features.websocket.*
import io.ktor.http.cio.websocket.*

//https://ktor.io/docs/creating-web-socket-chat.html#improving-our-solution

suspend fun DefaultClientWebSocketSession.outputMessages() {
    try {
        for (message in incoming) {
            message as? Frame.Text ?: continue
            EventPasser.channel.send(message.readText())
        }
    } catch (e: Exception) {
        println("Error while receiving: " + e.localizedMessage)
        return
    }
}

suspend fun DefaultClientWebSocketSession.inputMessages() {
    while (true) {
        val message = readLine() ?: ""
        if (message.equals("exit", true)) return
        try {
            send(message)
        } catch (e: Exception) {
            println("Error while sending: " + e.localizedMessage)
            return
        }
    }
}

suspend fun DefaultClientWebSocketSession.callApi() {
    //TODO
    this.send("")
}