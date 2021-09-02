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
    private val returnChannel = Channel<String>()
    override suspend fun getGroupMember(): ApiResponse<List<User>> =
        returnChannel.getReturnValue(ApiBuilder(GetGroupMemberList(groupId)).build())

    override suspend fun sendGroupMsg(msg: String): ApiResponse<SendPrivateMsgResponse> =
        returnChannel.getReturnValue(ApiBuilder(SendGroupMsg(groupId, msg)).build())

    override suspend fun setGroupKick(user_id: Long): ApiResponse<NullData> =
        returnChannel.getReturnValue(ApiBuilder(SetGroupKick(groupId, user_id)).build())

    override suspend fun setGroupAnonymousBan(
        anonymous: GroupMessageEvent.Anonymous,
        flag: String,
        duration: Long
    ): ApiResponse<NullData> =
        returnChannel.getReturnValue(ApiBuilder(SetGroupAnonymousBan(groupId, anonymous, flag, duration)).build())

    override suspend fun setGroupAdmin(user_id: Long, enable: Boolean): ApiResponse<NullData> =
        returnChannel.getReturnValue(ApiBuilder(SetGroupAdmin(groupId, user_id, enable)).build())

    override suspend fun setGroupCard(user_id: Long, card: String): ApiResponse<NullData> =
        returnChannel.getReturnValue(ApiBuilder(SetGroupCard(groupId, user_id, card)).build())

    override suspend fun setGroupName(name: String): ApiResponse<NullData> =
        returnChannel.getReturnValue(ApiBuilder(SetGroupName(groupId, name)).build())

    override suspend fun setGroupLeave(is_dismiss: Boolean): ApiResponse<NullData> =
        returnChannel.getReturnValue(ApiBuilder(SetGroupLeave(groupId, is_dismiss)).build())

    override suspend fun setGroupSpecialTitle(
        user_id: Long,
        specialTitle: String,
        duration: Long
    ): ApiResponse<NullData> =
        returnChannel.getReturnValue(ApiBuilder(SetGroupSpecialTitle(groupId, user_id, specialTitle, duration)).build())

    override suspend fun getGroupMemberInfo(user_id: Long, noCache: Boolean): ApiResponse<GetGroupMemberInfoResponse> =
        returnChannel.getReturnValue(ApiBuilder(GetGroupMemberInfo(groupId, user_id)).build())

    override suspend fun getGroupMemberInfoList(): ApiResponse<List<GetGroupMemberInfoResponse>> =
        returnChannel.getReturnValue(ApiBuilder(GetGroupMemberList(groupId)).build())

    override suspend fun getGroupHonor(type: String): ApiResponse<GetGroupHonorInfoResponse> =
        returnChannel.getReturnValue(ApiBuilder(GetGroupHonorInfo(groupId, type)).build())

    override suspend fun setGroupPortrait(file: String, cache: Int): ApiResponse<NullData> =
        returnChannel.getReturnValue(ApiBuilder(SetGroupPortrait(groupId, file, cache)).build())

    override suspend fun uploadGroupFile(file: String, name: String, folder: String): ApiResponse<NullData> =
        returnChannel.getReturnValue(ApiBuilder(UploadGroupFile(groupId, file, name, folder)).build())

    override suspend fun getGroupRootFiles(): ApiResponse<GetGroupRootFilesResponse> =
        returnChannel.getReturnValue(ApiBuilder(GetGroupRootFiles(groupId)).build())

    override suspend fun getGroupFilesByFolder(folder_id: String): ApiResponse<GetGroupFilesByFolderResponse> =
        returnChannel.getReturnValue(ApiBuilder(GetGroupFilesByFolder(groupId, folder_id)).build())

    override suspend fun getGroupFileUrl(file_id: String, busid: Int): ApiResponse<GetGroupFileUrlResponse> =
        returnChannel.getReturnValue(ApiBuilder(GetGroupFileUrl(groupId, file_id, busid)).build())

    override suspend fun getGroupAtAllRemain(): ApiResponse<GetGroupAtAllRemainResponse> =
        returnChannel.getReturnValue(ApiBuilder(GetGroupAtAllRemain(groupId)).build())

    override suspend fun sendGroupNotice(content: String): ApiResponse<NullData> =
        returnChannel.getReturnValue(ApiBuilder(SendGroupNotice(groupId, content)).build())

    override suspend fun getGroupMsgHistory(message_seq: Long): ApiResponse<GetGroupMsgHistoryResponse> =
        returnChannel.getReturnValue(ApiBuilder(GetGroupMsgHistory(message_seq, groupId)).build())

    override suspend fun getEssenceMsgList(): ApiResponse<GetEssenceMsgListResponse> =
        returnChannel.getReturnValue(ApiBuilder(GetEssenceMsgList(groupId)).build())

    override suspend fun getGroupFileSystemInfo(): ApiResponse<GetGroupFileSystemInfoResponse> =
        returnChannel.getReturnValue(ApiBuilder(GetGroupFileSystemInfo(groupId)).build())
}