@file:Suppress("unused")

package data.api

import com.fasterxml.jackson.annotation.JsonRawValue
import data.event.GroupMessageEvent
import listener.Api
import utils.ApiEndPoint

@ApiEndPoint("send_private_msg")
data class SendPrivateMsg(
    val user_id: Long,
    @JsonRawValue val message: String,
    val auto_escape: Boolean = false,
) : Api

@ApiEndPoint("send_private_msg")
data class SendGroupTempPrivateMsg(
    val user_id: Long,
    val group_id: Long,
    @JsonRawValue val message: String,
    val auto_escape: Boolean = false,
) : Api

@ApiEndPoint("send_group_msg")
data class SendGroupMsg(
    val group_id: Long,
    @JsonRawValue val message: String,
    val auto_escape: Boolean = false,
): Api

@ApiEndPoint("send_group_forward_msg")
data class SendGroupForwardMsg(
    val group_id: Long,
    val messages: List<MessageNode>,
): Api

@ApiEndPoint("send_msg")
data class SendMsg(
    val message_type: String,
    val user_id: Long,
    val group_id: Long,
    @JsonRawValue val message: String,
    val auto_escape: Boolean = false,
): Api

@ApiEndPoint("delete_msg")
data class DeleteMsg(
    val message_id: Int,
): Api

@ApiEndPoint("get_msg")
data class GetMsg(
    val message_id: Int,
): Api

@ApiEndPoint("get_forward_msg")
data class GetForwardMsg(
    val message_id: String,
): Api

@ApiEndPoint("get_image")
data class GetImage(
    val file: String,
): Api

@ApiEndPoint("set_group_kick")
data class SetGroupKick(
    val group_id: Long,
    val user_id: Long,
    val reject_add_request: Boolean = false,
): Api

@ApiEndPoint("set_group_ban")
data class SetGroupBan(
    val group_id: Long,
    val user_id: Long,
    val duration: Long,
): Api

//尚未支持
/*@ApiEndPoint("set_group_anonymous_ban")
data class SetGroupAnonymousBan(
    val group_id: Long,
    val anonymous: GroupMsgEvent.Anonymous,
val anonymous_flag: String,
val duration: Int,
)*/

@ApiEndPoint("set_group_whole_ban")
data class SetGroupWholeBan(
    val group_id: Long,
    val enable: Boolean = true,
) : Api

@ApiEndPoint("set_group_admin")
data class SetGroupAdmin(
    val group_id: Long,
    val user_id: Long,
    val enable: Boolean = true,
) : Api

//暂未支持
/*@ApiEndPoint("set_group_anonymous")
data class SetGroupAnonymous(
    val group_id: Long,
    val enable: Boolean = true,
): Api*/

data class SetGroupAnonymousBan(
    val group_id: Long,
    val anonymous: GroupMessageEvent.Anonymous,
    val flag: String,
    val duration: Long
) : Api

@ApiEndPoint("set_group_card")
data class SetGroupCard(
    val group_id: Long,
    val user_id: Long,
    val card: String = "",
) : Api

@ApiEndPoint("set_group_name")
data class SetGroupName(
    val group_id: Long,
    val group_name: String,
): Api

@ApiEndPoint("set_group_leave")
data class SetGroupLeave(
    val group_id: Long,
    val is_dismiss: Boolean = false,
): Api

@ApiEndPoint("set_group_special_title")
data class SetGroupSpecialTitle(
    val group_id: Long,
    val user_id: Long,
    val special_title: String = "",
    val duration: Long = -1,
): Api

@ApiEndPoint("set_friend_add_request")
data class SetFriendAddRequest(
    val flag: String,
    val approve: Boolean = true,
    val remark: String = "",
): Api

@ApiEndPoint("set_group_add_request")
data class SetGroupAddRequest(
    val flag: String,
    val sub_type : String,
    val approve: Boolean = true,
    val reason: String = "",
): Api

@ApiEndPoint("get_stranger_info")
data class GetStrangerInfo(
    val user_id: Long,
    val no_cache: Boolean = false,
): Api

@ApiEndPoint("delete_friend")
data class DeleteFriend(
    val friend_id: Long,
): Api

@ApiEndPoint("get_group_info")
data class GetGroupInfo(
    val group_id: Long,
    val no_cache: Boolean = false,
): Api

@ApiEndPoint("get_group_member_info")
data class GetGroupMemberInfo(
    val group_id: Long,
    val user_id: Long,
    val no_cache: Boolean = false,
): Api

@ApiEndPoint("get_group_member_list")
data class GetGroupMemberList(
    val group_id: Long,
): Api

@ApiEndPoint("get_group_honor_info")
data class GetGroupHonorInfo(
    val group_id: Long,
    val type: String,
): Api

//暂未支持
/*@ApiEndPoint("get_cookies")
data class GetCookies(
    val domain: String,
)

@ApiEndPoint("get_credentials")
data class GetCredentials(
    val domain: String,
)

@ApiEndPoint("get_record")
data class GetRecord(
    val file: String,
    val out_format: String,
)*/

@ApiEndPoint("set_restart")
data class SetRestart(
    val delay: Long,
): Api

@ApiEndPoint("set_group_portrait")
data class SetGroupPortrait(
    val group_id: Long,
    val file: String,
    val cache: Int,
): Api

@ApiEndPoint(".get_word_slices")
data class GetWordSlices(
    val content: String,
): Api

@ApiEndPoint("ocr_image")
data class OcrImage(
    val image: String,
): Api

@ApiEndPoint("upload_group_file")
data class UploadGroupFile(
    val group_id: Long,
    val file: String,
    val name: String,
    val folder: String,
): Api

@ApiEndPoint("get_group_file_system_info")
data class GetGroupFileSystemInfo(
    val group_id: Long,
): Api

@ApiEndPoint("get_group_root_files")
data class GetGroupRootFiles(
    val group_id: Long,
): Api

@ApiEndPoint("get_group_files_by_folder")
data class GetGroupFilesByFolder(
    val group_id: Long,
    val folder_id: String,
): Api

@ApiEndPoint("get_group_file_url")
data class GetGroupFileUrl(
    val group_id: Long,
    val file_id: String,
    val busid: Int,
): Api

@ApiEndPoint("get_group_at_all_remain")
data class GetGroupAtAllRemain(
    val group_id: Long,
): Api

/*@ApiEndPoint(".handle_quick_operation")
data class HandleQuickOperation(
    val context: --object,
val operation: --object,
)*/

@ApiEndPoint("_get_vip_info")
data class GetVipInfo(
    val user_id: Long,
): Api

@ApiEndPoint("_send_group_notice")
data class SendGroupNotice(
    val group_id: Long,
    val content: String,
): Api

@ApiEndPoint("reload_event_filter")
data class ReloadEventFilter(
    val file: String,
): Api

@ApiEndPoint("download_file")
data class DownloadFile(
    val url: String,
    val thread_count: Int,
    val headers: List<String>,
): Api

@ApiEndPoint("get_online_clients")
data class GetOnlineClients(
    val no_cache: Boolean,
): Api

@ApiEndPoint("get_group_msg_history")
data class GetGroupMsgHistory(
    val message_seq: Long,
    val group_id: Long,
): Api

@ApiEndPoint("set_essence_msg")
data class SetEssenceMsg(
    val message_id: Int,
): Api

@ApiEndPoint("delete_essence_msg")
data class DeleteEssenceMsg(
    val message_id: Int,
): Api

@ApiEndPoint("get_essence_msg_list")
data class GetEssenceMsgList(
    val group_id: Long,
): Api

@ApiEndPoint("check_url_safely")
data class CheckUrlSafely(
    val url: String,
): Api

@ApiEndPoint("_get_model_show")
data class GetModelShow(
    val model: String,
) : Api

@ApiEndPoint("_set_model_show")
data class SetModelShow(
    val model: String,
    val model_show: String,
) : Api

@ApiEndPoint("get_group_list")
class GetGroupList : Api

@ApiEndPoint("get_friend_list")
class GetFriendList : Api

@ApiEndPoint("get_login_info")
class GetLoginInfo : Api

@ApiEndPoint("can_send_image")
class CanSendImage : Api

@ApiEndPoint("can_send_record")
class CanSendRecord : Api

@ApiEndPoint("get_version_info")
class GetVersionInfo : Api

@ApiEndPoint("get_status")
class GetStatus : Api

@ApiEndPoint("get_group_system_msg")
class GetGroupSystemMsg : Api