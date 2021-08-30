package data

import listener.Event

//https://docs.go-cqhttp.org/event/

data class PrivateMsg(
    val font: Int,
    val message: List<Message>,
    val message_id: Int,
    val message_type: String,
    val post_type: String,
    val raw_message: String,
    val self_id: Long,
    val sender: Sender,
    val sub_type: String,
    val target_id: Long,
    val time: Long,
    val user_id: Long
): Event {
    data class Message(
        val `data`: Data,
        val type: String
    )

    data class Sender(
        val age: Int,
        val nickname: String,
        val sex: String,
        val user_id: Long
    )

    data class Data(
        val text: String
    )
}

data class FriendPrivateMsg (
    val data: PrivateMsg
): Event

data class GroupPrivateMsg (
    val data: PrivateMsg
): Event

data class GroupSelfPrivateMsg (
    val data: PrivateMsg
): Event

data class OtherPrivateMsg (
    val data: PrivateMsg
): Event

data class GroupMsgEvent(
    val anonymous: Anonymous?,
    val font: Int,
    val group_id: Long,
    val message: List<Message>,
    val message_id: Long,
    val message_seq: Int,
    val message_type: String,
    val post_type: String,
    val raw_message: String,
    val self_id: Int,
    val sender: Sender,
    val sub_type: String,
    val time: Long,
    val user_id: Long
): Event {
    data class Anonymous(
        val id: Long,
        val name: String,
        val flag: String
    )
    data class Message(
        val `data`: Data,
        val type: String
    )

    data class Sender(
        val age: Int,
        val area: String,
        val card: String,
        val level: String,
        val nickname: String,
        val role: String,
        val sex: String,
        val title: String,
        val user_id: Long
    )

    data class Data(
        val text: String
    )
}

data class NormalGroupMsg(
    val data: GroupMsgEvent
) : Event

data class AnonymousGroupMsg(
    val data: GroupMsgEvent
) : Event

data class NoticeGroupMsg(
    val data: GroupMsgEvent
) : Event

