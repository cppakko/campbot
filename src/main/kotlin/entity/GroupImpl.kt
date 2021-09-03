package entity

import api.ApiBuilder
import data.api.*
import data.event.GroupMessageEvent
import entity.interfaces.GroupInterface
import kotlinx.coroutines.channels.Channel
import utils.getReturnValue

class Group(
    override val groupId: Long
) : GroupInterface {
    /*override suspend fun getGroupMember(): ApiResponse<List<User>> =
        Channel<String>().getReturnValue(ApiBuilder(GetGroupMemberList(groupId)).build())*/

    override suspend fun sendGroupMsg(msg: String): ApiResponse<SendPrivateMsgResponse> =
        Channel<String>().getReturnValue(ApiBuilder(SendGroupMsg(groupId, msg)).build())

    override suspend fun setGroupKick(user_id: Long): ApiResponse<NullData> =
        Channel<String>().getReturnValue(ApiBuilder(SetGroupKick(groupId, user_id)).build())

    override suspend fun setGroupAnonymousBan(
        anonymous: GroupMessageEvent.Anonymous,
        flag: String,
        duration: Long
    ): ApiResponse<NullData> =
        Channel<String>().getReturnValue(ApiBuilder(SetGroupAnonymousBan(groupId, anonymous, flag, duration)).build())

    override suspend fun setGroupAdmin(user_id: Long, enable: Boolean): ApiResponse<NullData> =
        Channel<String>().getReturnValue(ApiBuilder(SetGroupAdmin(groupId, user_id, enable)).build())

    override suspend fun setGroupCard(user_id: Long, card: String): ApiResponse<NullData> =
        Channel<String>().getReturnValue(ApiBuilder(SetGroupCard(groupId, user_id, card)).build())

    override suspend fun setGroupName(name: String): ApiResponse<NullData> =
        Channel<String>().getReturnValue(ApiBuilder(SetGroupName(groupId, name)).build())

    override suspend fun setGroupLeave(is_dismiss: Boolean): ApiResponse<NullData> =
        Channel<String>().getReturnValue(ApiBuilder(SetGroupLeave(groupId, is_dismiss)).build())

    override suspend fun setGroupSpecialTitle(
        user_id: Long,
        specialTitle: String,
        duration: Long
    ): ApiResponse<NullData> =
        Channel<String>().getReturnValue(
            ApiBuilder(
                SetGroupSpecialTitle(
                    groupId,
                    user_id,
                    specialTitle,
                    duration
                )
            ).build()
        )

    override suspend fun getGroupMemberInfo(user_id: Long, noCache: Boolean): ApiResponse<GetGroupMemberInfoResponse> =
        Channel<String>().getReturnValue(ApiBuilder(GetGroupMemberInfo(groupId, user_id)).build())

    override suspend fun getGroupMemberInfoList(): ApiResponse<List<GetGroupMemberInfoResponse>> =
        Channel<String>().getReturnValue(ApiBuilder(GetGroupMemberList(groupId)).build())

    override suspend fun getGroupHonor(type: String): ApiResponse<GetGroupHonorInfoResponse> =
        Channel<String>().getReturnValue(ApiBuilder(GetGroupHonorInfo(groupId, type)).build())

    override suspend fun setGroupPortrait(file: String, cache: Int): ApiResponse<NullData> =
        Channel<String>().getReturnValue(ApiBuilder(SetGroupPortrait(groupId, file, cache)).build())

    override suspend fun uploadGroupFile(file: String, name: String, folder: String): ApiResponse<NullData> =
        Channel<String>().getReturnValue(ApiBuilder(UploadGroupFile(groupId, file, name, folder)).build())

    override suspend fun getGroupRootFiles(): ApiResponse<GetGroupRootFilesResponse> =
        Channel<String>().getReturnValue(ApiBuilder(GetGroupRootFiles(groupId)).build())

    override suspend fun getGroupFilesByFolder(folder_id: String): ApiResponse<GetGroupFilesByFolderResponse> =
        Channel<String>().getReturnValue(ApiBuilder(GetGroupFilesByFolder(groupId, folder_id)).build())

    override suspend fun getGroupFileUrl(file_id: String, busid: Int): ApiResponse<GetGroupFileUrlResponse> =
        Channel<String>().getReturnValue(ApiBuilder(GetGroupFileUrl(groupId, file_id, busid)).build())

    override suspend fun getGroupAtAllRemain(): ApiResponse<GetGroupAtAllRemainResponse> =
        Channel<String>().getReturnValue(ApiBuilder(GetGroupAtAllRemain(groupId)).build())

    override suspend fun sendGroupNotice(content: String): ApiResponse<NullData> =
        Channel<String>().getReturnValue(ApiBuilder(SendGroupNotice(groupId, content)).build())

    override suspend fun getGroupMsgHistory(message_seq: Long): ApiResponse<GetGroupMsgHistoryResponse> =
        Channel<String>().getReturnValue(ApiBuilder(GetGroupMsgHistory(message_seq, groupId)).build())

    override suspend fun getEssenceMsgList(): ApiResponse<GetEssenceMsgListResponse> =
        Channel<String>().getReturnValue(ApiBuilder(GetEssenceMsgList(groupId)).build())

    override suspend fun getGroupFileSystemInfo(): ApiResponse<GetGroupFileSystemInfoResponse> =
        Channel<String>().getReturnValue(ApiBuilder(GetGroupFileSystemInfo(groupId)).build())
}