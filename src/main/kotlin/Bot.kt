import entity.BotUtilImpl
import event.EventLogger
import event.EventManager
import event.EventPasser
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import mu.KotlinLogging
import network.websocket.WebSocketHelper

class Bot(
    val ipAddress: String,
    val port: Int
) {
    //TODO 优化初始化顺序 确保不出现NULL
    lateinit var rootPathClientJob: Job
    private val logger = KotlinLogging.logger {}
    val eventManager = EventManager(this)
    private var isRunning: Boolean = false
    val utils = BotUtilImpl()

    suspend fun open() {
        coroutineScope {
            isRunning = true
            launch { EventPasser(this@Bot).run() }
            launch { EventLogger().init() }
            launch { WebSocketHelper().connectionInit(this@Bot) }
        }
    }

    suspend fun close() {
        if (isRunning) {
            rootPathClientJob.cancelAndJoin()
        } else logger.warn { "Bot当前没有在运行!!" }
    }
}