package data.api

data class MessageNode(
    val id: Int,
    val name: String,
    val uin: Long,
    val content: String,
    val seq: String
)

data class Sender(
    val nickname: String,
    val user_id: Long,
)

data class Message(
    val data: MessageData,
    val retcode: Int,
    val status: String,
)

data class MessageData(
    val messages: List<MessageContent>
)

data class MessageContent(
    val content: String,
    val sender: Sender,
    val time: Int
)

data class CurrentTalkative(
    val user_id: Long,
    val nickname: String,
    val avatar: String,
    val day_count: Int,
)

data class HonorInfoEntry(
    val user_id: Long,
    val nickname: String,
    val avatar: String,
    val description: String
)

data class InvitedRequest(
    val request_id: Long,
    val invitor_uin: Long,
    val invitor_nick: String,
    val group_id: Long,
    val group_name: String,
    val checked: Boolean,
    val actor: Long,
)

data class JoinRequest(
    val request_id: Long,
    val invitor_uin: Long,
    val invitor_nick: String,
    val group_id: Long,
    val group_name: String,
    val checked: Boolean,
    val actor: Long,
)

data class File(
    val file_id: String,
    val file_name: String,
    val busid: Int,
    val file_size: Long,
    val upload_time: Long,
    val dead_time: Long,
    val modify_time: Long,
    val download_times: Int,
    val uploader: Long,
    val uploader_name: String,
)

data class Folder(
    val folder_id: String,
    val folder_name: String,
    val create_time: Long,
    val creator: Long,
    val creator_name: String,
    val total_file_count: Int,
)

data class Statistics(
    val packet_received: ULong,
    val packet_sent: ULong,
    val packet_lost: UInt,
    val message_received: ULong,
    val message_sent: ULong,
    val disconnect_times: UInt,
    val lost_times: UInt,
)

data class Device(
    val app_id: Long,
    val device_name: String,
    val device_kind: String,
)

data class ClientModel(
    val model_show: String,
    val need_pay: Boolean,
)

data class TextDetection(
    val text: String,
    val confidence: Int,
    val coordinates: Pair<Int, Int>
)

data class Friend(
    val user_id: Long,
    val nickname: String,
    val remark: String
)