package data.event

import listener.PassiveEvent

data class LifeCycleEvent(
    val _post_method: Int,
    val meta_event_type: String,
    val post_type: String,
    val self_id: Int,
    val sub_type: String,
    val time: Int
) : PassiveEvent

data class HeartEvent(
    val interval: Int,
    val meta_event_type: String,
    val post_type: String,
    val self_id: Long,
    val status: Status,
    val time: Int
) : PassiveEvent {
    data class Status(
        val app_enabled: Boolean,
        val app_good: Boolean,
        val app_initialized: Boolean,
        val good: Boolean,
        val online: Boolean,
        val plugins_good: Any?,
        val stat: Stat
    )

    data class Stat(
        val disconnect_times: Int,
        val last_message_time: Long,
        val lost_times: Int,
        val message_received: Int,
        val message_sent: Int,
        val packet_lost: Int,
        val packet_received: Int,
        val packet_sent: Int
    )
}