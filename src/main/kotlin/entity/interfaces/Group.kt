package entity.interfaces

import data.api.*
import data.event.GroupMessageEvent

interface GroupInterface {
    val groupId: Long

    //suspend fun getGroupMember(): ApiResponse<List<User>>
    suspend fun sendGroupMsg(msg: String): ApiResponse<SendPrivateMsgResponse>
    suspend fun setGroupKick(user_id: Long): ApiResponse<NullData>
    suspend fun setGroupAnonymousBan(
        anonymous: GroupMessageEvent.Anonymous,
        flag: String,
        duration: Long
    ): ApiResponse<NullData>

    suspend fun setGroupAdmin(user_id: Long, enable: Boolean): ApiResponse<NullData>
    suspend fun setGroupCard(user_id: Long, card: String): ApiResponse<NullData>
    suspend fun setGroupName(name: String): ApiResponse<NullData>
    suspend fun setGroupLeave(is_dismiss: Boolean = false): ApiResponse<NullData>
    suspend fun setGroupSpecialTitle(user_id: Long, specialTitle: String, duration: Long): ApiResponse<NullData>
    suspend fun getGroupMemberInfo(user_id: Long, noCache: Boolean = false): ApiResponse<GetGroupMemberInfoResponse>
    suspend fun getGroupMemberInfoList(): ApiResponse<List<GetGroupMemberInfoResponse>>
    suspend fun getGroupHonor(type: String): ApiResponse<GetGroupHonorInfoResponse>
    suspend fun setGroupPortrait(file: String, cache: Int): ApiResponse<NullData>
    suspend fun uploadGroupFile(file: String, name: String, folder: String): ApiResponse<NullData>
    suspend fun getGroupRootFiles(): ApiResponse<GetGroupRootFilesResponse>
    suspend fun getGroupFilesByFolder(folder_id: String): ApiResponse<GetGroupFilesByFolderResponse>
    suspend fun getGroupFileUrl(file_id: String, busid: Int): ApiResponse<GetGroupFileUrlResponse>
    suspend fun getGroupAtAllRemain(): ApiResponse<GetGroupAtAllRemainResponse>
    suspend fun sendGroupNotice(content: String): ApiResponse<NullData>
    suspend fun getGroupMsgHistory(message_seq: Long): ApiResponse<GetGroupMsgHistoryResponse>
    suspend fun getEssenceMsgList(): ApiResponse<GetEssenceMsgListResponse>
    suspend fun getGroupFileSystemInfo(): ApiResponse<GetGroupFileSystemInfoResponse>
}