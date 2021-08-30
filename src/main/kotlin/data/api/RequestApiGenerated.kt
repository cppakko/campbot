@file:Suppress("unused")

package data.api

import data.GroupMsgEvent
import listener.Api
import utils.ApiEndPoint

@ApiEndPoint("/send_private_msg")
data class SendPrivateMsg(
    val user_id: Long,
    val group_id: Long,
    val message: String,
    val auto_escape: Boolean,
)

@ApiEndPoint("/send_group_msg")
data class SendGroupMsg(
    val group_id: Long,
    val message: String,
    val auto_escape: Boolean,
)

@ApiEndPoint("/send_group_forward_msg")
data class SendGroupForwardMsg(
    val group_id: Long,
    val messages: List<MessageNode>,
)

@ApiEndPoint("/send_msg")
data class SendMsg(
    val message_type: String,
    val user_id: Long,
    val group_id: Long,
    val message: String,
    val auto_escape: Boolean,
)

@ApiEndPoint("/delete_msg")
data class DeleteMsg(
    val message_id: Int,
)

@ApiEndPoint("/get_msg")
data class GetMsg(
    val message_id: Int,
)

@ApiEndPoint("/get_forward_msg")
data class GetForwardMsg(
    val message_id: String,
)

@ApiEndPoint("/get_image")
data class GetImage(
    val file: String,
)

@ApiEndPoint("/set_group_kick")
data class SetGroupKick(
    val group_id: Long,
    val user_id: Long,
    val reject_add_request: Boolean,
)

@ApiEndPoint("/set_group_ban")
data class SetGroupBan(
    val group_id: Long,
    val user_id: Long,
    val duration: Int,
)

@ApiEndPoint("/set_group_anonymous_ban")
data class SetGroupAnonymousBan(
    val group_id: Long,
    val anonymous: GroupMsgEvent.Anonymous,
val anonymous_flag: String,
val duration: Int,
)

@ApiEndPoint("/set_group_whole_ban")
data class SetGroupWholeBan(
    val group_id: Long,
    val enable: Boolean,
)

@ApiEndPoint("/set_group_admin")
data class SetGroupAdmin(
    val group_id: Long,
    val user_id: Long,
    val enable: Boolean,
)

@ApiEndPoint("/set_group_anonymous")
data class SetGroupAnonymous(
    val group_id: Long,
    val enable: Boolean,
)

@ApiEndPoint("/set_group_card")
data class SetGroupCard(
    val group_id: Long,
    val user_id: Long,
    val card: String,
)

@ApiEndPoint("/set_group_name")
data class SetGroupName(
    val group_id: Long,
    val group_name: String,
)

@ApiEndPoint("/set_group_leave")
data class SetGroupLeave(
    val group_id: Long,
    val is_dismiss: Boolean,
)

@ApiEndPoint("/set_group_special_title")
data class SetGroupSpecialTitle(
    val group_id: Long,
    val user_id: Long,
    val special_title: String,
    val duration: Int,
)

@ApiEndPoint("/set_friend_add_request")
data class SetFriendAddRequest(
    val flag: String,
    val approve: Boolean,
    val remark: String,
)

@ApiEndPoint("/set_group_add_request")
data class SetGroupAddRequest(
    val flag: String,
    val sub_type : String,
    val approve: Boolean,
    val reason: String,
)

@ApiEndPoint("/get_stranger_info")
data class GetStrangerInfo(
    val user_id: Long,
    val no_cache: Boolean,
)

@ApiEndPoint("/delete_friend")
data class DeleteFriend(
    val friend_id: Long,
)

@ApiEndPoint("/get_group_info")
data class GetGroupInfo(
    val group_id: Long,
    val no_cache: Boolean,
)

@ApiEndPoint("/get_group_member_info")
data class GetGroupMemberInfo(
    val group_id: Long,
    val user_id: Long,
    val no_cache: Boolean,
)

@ApiEndPoint("/get_group_member_list")
data class GetGroupMemberList(
    val group_id: Long,
)

@ApiEndPoint("/get_group_honor_info")
data class GetGroupHonorInfo(
    val group_id: Long,
    val type: String,
)

@ApiEndPoint("/get_cookies")
data class GetCookies(
    val domain: String,
)

@ApiEndPoint("/get_credentials")
data class GetCredentials(
    val domain: String,
)

@ApiEndPoint("/get_record")
data class GetRecord(
    val file: String,
    val out_format: String,
)

@ApiEndPoint("/set_restart")
data class SetRestart(
    val delay: Int,
)

@ApiEndPoint("/set_group_portrait")
data class SetGroupPortrait(
    val group_id: Long,
    val file: String,
    val cache: Int,
)

@ApiEndPoint("/.get_word_slices")
data class GetWordSlices(
    val content: String,
)

@ApiEndPoint("/ocr_image")
data class OcrImage(
    val image: String,
)

@ApiEndPoint("/upload_group_file")
data class UploadGroupFile(
    val group_id: Long,
    val file: String,
    val name: String,
    val folder: String,
)

@ApiEndPoint("/get_group_file_system_info")
data class GetGroupFileSystemInfo(
    val group_id: Long,
)

@ApiEndPoint("/get_group_root_files")
data class GetGroupRootFiles(
    val group_id: Long,
)

@ApiEndPoint("/get_group_files_by_folder")
data class GetGroupFilesByFolder(
    val group_id: Long,
    val folder_id: String,
)

@ApiEndPoint("/get_group_file_url")
data class GetGroupFileUrl(
    val group_id: Long,
    val file_id: String,
    val busid: Int,
)

@ApiEndPoint("/get_group_at_all_remain")
data class GetGroupAtAllRemain(
    val group_id: Long,
)

//@ApiEndPoint("/.handle_quick_operation")
//data class HandleQuickOperation(
//    val context: --object,
//val operation: --object,
//)

@ApiEndPoint("/_get_vip_info")
data class GetVipInfo(
    val user_id: Long,
)

@ApiEndPoint("/_send_group_notice")
data class SendGroupNotice(
    val group_id: Long,
    val content: String,
)

@ApiEndPoint("/reload_event_filter")
data class ReloadEventFilter(
    val file: String,
)

@ApiEndPoint("/download_file")
data class DownloadFile(
    val url: String,
    val thread_count: Int,
    val headers: List<String>,
)

@ApiEndPoint("/get_online_clients")
data class GetOnlineClients(
    val no_cache: Boolean,
)

@ApiEndPoint("/get_group_msg_history")
data class GetGroupMsgHistory(
    val message_seq: Long,
    val group_id: Long,
)

@ApiEndPoint("/set_essence_msg")
data class SetEssenceMsg(
    val message_id: Int,
)

@ApiEndPoint("/delete_essence_msg")
data class DeleteEssenceMsg(
    val message_id: Int,
)

@ApiEndPoint("/get_essence_msg_list")
data class GetEssenceMsgList(
    val group_id: Long,
)

@ApiEndPoint("/check_url_safely")
data class CheckUrlSafely(
    val url: String,
)

@ApiEndPoint("/_get_model_show")
data class GetModelShow(
    val model: String,
)

@ApiEndPoint("/_set_model_show")
data class SetModelShow(
    val model: String,
    val model_show: String,
)

