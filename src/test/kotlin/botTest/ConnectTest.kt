package botTest

import Bot
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
        }
    }
}