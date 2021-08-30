//package data.api
//
//import listener.Api
//import utils.ApiEndPoint
//
//@ApiEndPoint("send_private_msg")
//data class PrivateMsg (
//    val user_id: Long,
//    val group_id: Long,
//    val message: String,
//    val auto_escape: Boolean
//    ): Api
//
//@ApiEndPoint("send_group_msg")
//data class GroupMsg (
//    val group_id: Long,
//    val message: String,
//    val auto_escape: Boolean
//        ): Api
//
//@ApiEndPoint("send_group_forward_msg")
//data class GroupForwardMsg(
//    val group_id: Long,
//    val messages: List<MessageNode>,
//): Api
//
//data class MessageNode(
//    val id: Int,
//    val name: String,
//    val uin: Long,
//    val content: String,
//    val seq: String
//)
//
//@ApiEndPoint("send_msg")
//data class SendMsg(
//    val message_type: String,
//    val user_id: Long,
//    val group_id: Long,
//    val message: String,
//    val auto_escape: Boolean = false
//): Api
//
//@ApiEndPoint("delete_msg")
//data class GetDeleteMsg(
//    val message_id: Int
//)
//
//@ApiEndPoint("get_msg")
//data class GetMsg(
//    val message_id: Int
//): Api
//
//@ApiEndPoint("get_forward_msg")
//data class GetForWardMsg(
//    val message_id: Int
//): Api
//
//@ApiEndPoint("get_image")
//data class GetImage(
//    val file: String
//)
//
