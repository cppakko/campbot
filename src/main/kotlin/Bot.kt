import entity.BotUtilImpl
import event.EventManager
import event.EventPasser
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import network.websocket.WebSocketHelper

class Bot(
    val ipAddress: String,
    val port: Int,
    val accessToken: String = ""
) {
    //TODO 优化初始化顺序 确保不出现NULL
    lateinit var rootPathClientJob: Job
    val eventManager = EventManager(this)
    private var isRunning: Boolean = false
    val utils = BotUtilImpl()

    suspend fun open() {
        coroutineScope {
            isRunning = true
            launch { EventPasser(this@Bot).run() }
            launch { WebSocketHelper().connectionInit(this@Bot) }
        }
    }

}