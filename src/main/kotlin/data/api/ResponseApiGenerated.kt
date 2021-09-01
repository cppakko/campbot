@file:Suppress("unused")

package data.api

import com.fasterxml.jackson.annotation.JsonRawValue

data class SendPrivateMsgResponse(
    val message_id: Int,
)

data class SendGroupMsgResponse(
    val message_id: Int,
)

data class SendMsgResponse(
    val message_id: Int,
)

data class GetMsgResponse(
    val message_id: Int,
    val real_id: Int,
    val sender: Sender,
    val time: Int,
    @JsonRawValue val message: String,
    val raw_message: String,
)

data class GetForwardMsgResponse(
    val messages: List<Message>,
)

data class GetImageResponse(
    val size: Int,
    val filename: String,
    val url: String,
)

data class GetLoginInfoResponse(
    val user_id: Long,
    val nickname: String,
)

data class GetStrangerInfoResponse(
    val user_id: Long,
    val nickname: String,
    val sex: String,
    val age: Int,
    val qid: String,
)

data class GetFriendListResponse(
    val user_id: Long,
    val nickname: String,
    val remark: String,
)

//note: api:"/get_group_list" returns List<GetGroupInfoResponse>

data class GetGroupInfoResponse(
    val group_id: Long,
    val group_name: String,
    val group_memo: String,
    val group_create_time: Int,
    val group_level: Int,
    val member_count: Int,
    val max_member_count: Int,
)

data class GetGroupMemberInfoResponse(
    val group_id: Long,
    val user_id: Long,
    val nickname: String,
    val card: String,
    val sex: String,
    val age: Int,
    val area: String,
    val join_time: Int,
    val last_sent_time: Int,
    val level: String,
    val role: String,
    val unfriendly: Boolean,
    val title: String,
    val title_expire_time: Long,
    val card_changeable: Boolean,
)


data class GetGroupHonorInfoResponse(
    val group_id: Long,
    val current_talkative: CurrentTalkative,
    val talkative_list: List<HonorInfoEntry>,
    val performer_list: List<HonorInfoEntry>,
    val legend_list: List<HonorInfoEntry>,
    val strong_newbie_list: List<HonorInfoEntry>,
    val emotion_list: List<HonorInfoEntry>,
)

data class GetCookiesResponse(
    val cookies: String,
)

data class GetCsrfTokenResponse(
    val token: Int,
)

data class GetCredentialsResponse(
    val cookies: String,
    val csrf_token: Int,
)

data class GetRecordResponse(
    val file: String,
)

data class CanSendImageResponse(
    val yes: Boolean,
)

data class CanSendRecordResponse(
    val yes: Boolean,
)

data class GetVersionInfoResponse(
    val app_name: String,
    val app_version: String,
    val app_full_name: String,
    val protocol_version: String,
    val coolq_edition: String,
    val coolq_directory: String,
    val go_cqhttp: Boolean,
    val plugin_version: String,
    val plugin_build_number: Int,
    val plugin_build_configuration: String,
    val runtime_version: String,
    val runtime_os: String,
    val version: String,
    val protocol: Int,
)

data class GetWordSlicesResponse(
    val slices: List<String>,
)

data class OcrImageResponse(
    val texts: List<TextDetection>,
    val language: String,
)


data class GetGroupSystemMsgResponse(
    val invited_requests: List<InvitedRequest>,
    val join_requests: List<JoinRequest>,
)

data class GetGroupFileSystemInfoResponse(
    val file_count: Int,
    val limit_count: Int,
    val used_space: Long,
    val total_space: Long,
)

data class GetGroupRootFilesResponse(
    val files: List<File>,
    val folders: List<Folder>,
)

data class GetGroupFilesByFolderResponse(
    val files: List<File>,
    val folders: List<Folder>,
)


data class GetGroupFileUrlResponse(
    val url: String,
)

data class GetStatusResponse(
    val app_initialized: Boolean,
    val app_enabled: Boolean,
    val plugins_good: Boolean,
    val app_good: Boolean,
    val online: Boolean,
    val good: Boolean,
    val stat: Statistics,
)

data class GetGroupAtAllRemainResponse(
    val can_at_all: Boolean,
    val remain_at_all_count_for_group: Int,
    val remain_at_all_count_for_uin: Int,
)

data class GetVipInfoResponse(
    val user_id: Long,
    val nickname: String,
    val level: Long,
    val level_speed: Double,
    val vip_level: String,
    val vip_growth_speed: Long,
    val vip_growth_total: Long,
)

data class DownloadFileResponse(
    val file: String,
)

data class GetOnlineClientsResponse(
    val clients: List<Device>,
)

data class GetGroupMsgHistoryResponse(
    val messages: List<Message>,
)

data class GetEssenceMsgListResponse(
    val sender_id: Long,
    val sender_nick: String,
    val sender_time: Long,
    val operator_id: Long,
    val operator_nick: String,
    val operator_time: Long,
    val message_id: Int,
)

data class CheckUrlSafelyResponse(
    val level: Int,
)

data class GetModelShowResponse(
    val variants: List<ClientModel>
)


data class QidianGetAccountInfoResponse(
    val master_id: Long,
    val ext_name: String,
    val create_time: Long,
)

