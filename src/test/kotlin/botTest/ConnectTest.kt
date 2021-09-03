package botTest

import Bot
import io.ktor.client.*
import io.ktor.client.features.websocket.*
import io.ktor.http.*
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.*
import org.junit.jupiter.api.Test

class ConnectTest {
    @Test
    fun openAndCloseTest() {
        val bot = Bot("127.0.0.1", 6700)
        runBlocking(Dispatchers.IO) {
            val job = launch {
                bot.open()
            }
            delay(1000)
        }
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

suspend fun DefaultClientWebSocketSession.outputMessages(str: String) {
    try {
        for (message in incoming) {
            message as? Frame.Text ?: continue
            println(str + message.readText())
        }
    } catch (e: Exception) {
        println("Error while receiving: " + e.localizedMessage)
    }
}

fun main() {
    val client = HttpClient {
        install(WebSockets)
    }
    runBlocking {
        launch {
            client.webSocket(method = HttpMethod.Get, host = "127.0.0.1", port = 6700, path = "/api") {
                val messageOutputRoutine = launch { outputMessages("1    ") }
                val userInputRoutine = launch { inputMessages() }

                userInputRoutine.join() // Wait for completion; either "exit" or error
                messageOutputRoutine.cancelAndJoin()
            }
        }
        launch {
            client.webSocket(method = HttpMethod.Get, host = "127.0.0.1", port = 6700, path = "/api") {
                val messageOutputRoutine = launch { outputMessages("2    ") }
                delay(10000)
                send("iojjijiji")
                messageOutputRoutine.join()
            }
        }
    }
    client.close()
    println("Connection closed. Goodbye!")
}