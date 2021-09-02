package entity.interfaces

import data.api.*
import entity.Group
import entity.GroupUser
import entity.User

interface BotUtil {
    /**
     *
     * @param flag String 加好友请求的 flag（需从上报的数据中获得)
     * @param approve Boolean 是否同意请求
     * @param remark String 添加后的好友备注（仅在同意时有效）
     * @return ApiResponse<NullData>
     */
    suspend fun setFriendAddRequest(flag: String, approve: Boolean, remark: String = ""): ApiResponse<NullData>
    suspend fun setGroupAddRequest(
        flag: String,
        sub_type: String,
        approve: Boolean = true,
        reason: String
    ): ApiResponse<NullData>

    suspend fun getBotInfo(): ApiResponse<GetLoginInfoResponse>
    suspend fun getStrangerInfo(user_id: Long, noCache: Boolean = false): ApiResponse<GetStrangerInfoResponse>
    suspend fun getFriendList(): ApiResponse<Array<Friend>>
    suspend fun deleteFriend(friend_id: Long): ApiResponse<NullData>
    suspend fun getGroupInfo(group_id: Long, noCache: Boolean = false): ApiResponse<GetGroupInfoResponse>
    suspend fun getGroupInfoList(): ApiResponse<List<GetGroupInfoResponse>>
    suspend fun canSendImage(): ApiResponse<CanSendImageResponse>
    suspend fun canSendRecord(): ApiResponse<CanSendRecordResponse>
    suspend fun getVersionInfo(): ApiResponse<GetVersionInfoResponse>
    suspend fun setGoCqhttpRestart(delay: Long): ApiResponse<NullData>
    suspend fun ocrImage(image_id: String): ApiResponse<OcrImageResponse>
    suspend fun getStatus(): ApiResponse<GetStatusResponse>
    suspend fun reloadEventFilter(file: String): ApiResponse<NullData>
    suspend fun downloadFile(url: String, thread_count: Int, headers: List<String>): ApiResponse<DownloadFileResponse>
    suspend fun getOnlineClients(noCache: Boolean): ApiResponse<GetOnlineClientsResponse>
    suspend fun setEssenceMsg(message_id: Int): ApiResponse<NullData>
    suspend fun deleteEssenceMsg(message_id: Int): ApiResponse<NullData>
    suspend fun checkUrlSafely(url: String): ApiResponse<CheckUrlSafelyResponse>
    suspend fun getModelShow(model: String): ApiResponse<GetModelShowResponse>
    suspend fun setModelShow(model: String, model_show: String): ApiResponse<NullData>
    suspend fun getGroupSystemMsg(): ApiResponse<GetGroupSystemMsgResponse>
    suspend fun getMsg(message_id: Int): ApiResponse<GetMsgResponse>
    suspend fun getForwardMsg(message_id: Int): ApiResponse<List<GetForwardMsgResponse>>
    suspend fun getImage(file: String): ApiResponse<GetImageResponse>

    suspend fun getFriendById(user_id: Long): User
    suspend fun getFriendByIdSafely(user_id: Long): User
    suspend fun getGroupById(group_id: Long): Group
    suspend fun getGroupByIdSafely(group_id: Long): Group
    suspend fun getGroupUserById(user_id: Long, group_id: Long): GroupUser
    suspend fun getGroupUserByIdSafely(user_id: Long, group_id: Long): GroupUser

}