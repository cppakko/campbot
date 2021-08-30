import event.EventLogger
import event.EventManager
import event.EventPasser
import io.ktor.client.features.websocket.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import mu.KotlinLogging
import network.websocket.WsInit

class Bot(
    val ipAddress: String,
    val port: Int
) {
    //TODO 优化初始化顺序 确保不出现NULL
    lateinit var rootPathClientJob: Job
    lateinit var apiClientWebSocketSession: DefaultClientWebSocketSession
    private val logger = KotlinLogging.logger {}
    val eventManager = EventManager(this)
    var isRunning: Boolean = false

    suspend fun open() {
        coroutineScope {
            isRunning = true
            launch { EventPasser(this@Bot).run() }
            launch { EventLogger().init() }
            launch { WsInit().wsInit(this@Bot) }
        }
    }

    suspend fun close() {
        if (isRunning) {
            rootPathClientJob.cancelAndJoin()
        }
        else logger.warn { "Bot当前没有在运行!!" }
    }
}