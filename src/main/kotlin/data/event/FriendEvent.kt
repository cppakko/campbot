package data.event

import listener.PassiveEvent
import listener.PrivateMsgEvent

data class FriendAdd(
    val time: Long,
    val self_id: Long,
    val post_type: String,
    val notice_type: String,
    val user_id: Long
): PassiveEvent

data class FriendRecall(
    val time: Long,
    val self_id: Long,
    val post_type: String,
    val notice_type: String,
    val user_id: Long,
    val message_id: Long
): PrivateMsgEvent

data class FriendPoke(
    val post_type: String,
    val notice_type: String,
    val sub_type: String,
    val self_id: Long,
    val sender_id: Long,
    val user_id: Long,
    val target_id: Long,
    val time: Long
): PrivateMsgEvent

data class OfflineFile(
    val post_type: String,
    val notice_type: String,
    val user_id: Long,
    val `file`: File
): PrivateMsgEvent {
    data class File(
        val name: String,
        val size: Long,
        val url: String
    )
}

data class FriendAddRequest(
    val time: Long,
    val self_id: Long,
    val post_type: String,
    val request_type: String,
    val user_id: Long,
    val comment: String,
    val flag: String
): PassiveEvent