package data.event

import api.ApiBuilder
import com.fasterxml.jackson.annotation.JsonRawValue
import com.fasterxml.jackson.databind.ObjectMapper
import data.api.ApiResponse
import data.api.HandleQuickOperation
import data.api.NullData
import kotlinx.coroutines.channels.Channel
import listener.GroupMsgEvent
import listener.PrivateMsgEvent
import utils.getReturnValue

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
): PrivateMsgEvent {
    suspend fun reply(msg: String, auto_escape: Boolean = false): ApiResponse<NullData> =
        Channel<String>()
            .getReturnValue(
                ApiBuilder(
                    HandleQuickOperation(
                        this,
                        ObjectMapper().writeValueAsString(ReplyData(msg, auto_escape))
                    )
                ).build()
            )

    private data class ReplyData(
        @JsonRawValue val reply: String,
        @JsonRawValue val auto_escape: Boolean
    )

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
): PrivateMsgEvent

data class GroupPrivateMsg (
    val data: PrivateMsg
): PrivateMsgEvent

data class GroupSelfPrivateMsg (
    val data: PrivateMsg
): PrivateMsgEvent

data class OtherPrivateMsg (
    val data: PrivateMsg
): PrivateMsgEvent

data class GroupMessageEvent(
    val anonymous: Anonymous?,
    val font: Int,
    val group_id: Long,
    val message: List<*>,
    val message_id: Long,
    val message_seq: Int,
    val message_type: String,
    val post_type: String,
    val raw_message: String,
    val self_id: Long,
    val sender: Sender,
    val sub_type: String,
    val time: Long,
    val user_id: Long
): GroupMsgEvent {
    suspend fun reply(msg: String, auto_escape: Boolean = false, at_sender: Boolean = false): ApiResponse<NullData> =
        Channel<String>().getReturnValue(
            ApiBuilder(
                HandleQuickOperation(
                    this,
                    ObjectMapper().writeValueAsString(ReplyData(msg, auto_escape, at_sender))
                )
            ).build()
        )

    suspend fun delete(): ApiResponse<NullData> =
        Channel<String>().getReturnValue(
            ApiBuilder(
                HandleQuickOperation(
                    this,
                    ObjectMapper().writeValueAsString(DeleteData(true))
                )
            ).build()
        )

    suspend fun kick(): ApiResponse<NullData> =
        Channel<String>().getReturnValue(
            ApiBuilder(
                HandleQuickOperation(
                    this,
                    ObjectMapper().writeValueAsString(KickData(true))
                )
            ).build()
        )

    suspend fun ban(duration: Long): ApiResponse<NullData> =
        Channel<String>().getReturnValue(
            ApiBuilder(
                HandleQuickOperation(
                    this,
                    ObjectMapper().writeValueAsString(BanData(true, duration))
                )
            ).build()
        )

    private data class DeleteData(
        val delete: Boolean
    )

    private data class KickData(
        val kick: Boolean
    )

    private data class ReplyData(
        val reply: String,
        val auto_escape: Boolean,
        val at_sender: Boolean
    )

    private data class BanData(
        val ban: Boolean,
        val ban_duration: Long
    )

    data class Anonymous(
        val id: Long,
        val name: String,
        val flag: String
    )

    private data class Message(
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

    private data class Data(
        val text: String
    )
}

data class NormalGroupMsg(
    val data: GroupMessageEvent
) : GroupMsgEvent

data class AnonymousGroupMsg(
    val data: GroupMessageEvent
) : GroupMsgEvent

data class NoticeGroupMsg(
    val data: GroupMessageEvent
) : GroupMsgEvent

