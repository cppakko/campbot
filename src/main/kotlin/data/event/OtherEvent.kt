package data.event

import listener.PassiveEvent

data class OtherClientStatusChangeEvent(
    val post_type: String,
    val notice_type: String,
    val client: Device,
    val online: Boolean
): PassiveEvent {
    data class Device(
        val app_id: Long,
        val device_name: String,
        val device_kind: String
    )
}

data class EssenceEvent(
    val post_type: String,
    val notice_type: String,
    val sub_type: String,
    val sender_id: Long,
    val operator_id: Long,
    val message_id: Int
): PassiveEvent

data class AddEssenceEvent(
    val data: EssenceEvent
): PassiveEvent

data class DeleteEssenceEvent(
    val data: EssenceEvent
): PassiveEvent