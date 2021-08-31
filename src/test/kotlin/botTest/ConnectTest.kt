package botTest

import Bot
import kotlinx.coroutines.*
import message.MessageBuilder
import org.junit.jupiter.api.Test

class ConnectTest {
    @Test
    fun openAndCloseTest() {
        val bot = Bot("127.0.0.1", 6700)
        runBlocking(Dispatchers.IO) {
            val job = launch {
                bot.open()
            }
            delay(10000)
            val msg = MessageBuilder().addLocation("39.8969426","116.3109099","test","test").build()
            bot.utils.getGroupById(1041084231).sendGroupMsg(msg)
        }
    }
}