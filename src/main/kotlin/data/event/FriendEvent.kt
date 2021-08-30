package data.event

import listener.Event

data class FriendAdd(
    val time: Long,
    val self_id: Long,
    val post_type: String,
    val notice_type: String,
    val user_id: Long
): Event

data class FriendRecall(
    val time: Long,
    val self_id: Long,
    val post_type: String,
    val notice_type: String,
    val user_id: Long,
    val message_id: Long
): Event

data class FriendPoke(
    val post_type: String,
    val notice_type: String,
    val sub_type: String,
    val self_id: Long,
    val sender_id: Long,
    val user_id: Long,
    val target_id: Long,
    val time: Long
): Event

data class OfflineFile(
    val post_type: String,
    val notice_type: String,
    val user_id: Long,
    val `file`: File
): Event {
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
): Event