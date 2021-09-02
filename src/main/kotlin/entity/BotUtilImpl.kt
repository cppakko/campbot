package entity

import api.ApiBuilder
import data.api.*
import entity.interfaces.BotUtil
import exceptions.FriendNotFoundException
import exceptions.GroupNotFoundException
import exceptions.GroupUserNotFoundException
import kotlinx.coroutines.channels.Channel
import utils.getReturnValue

class BotUtilImpl : BotUtil {
    private val returnChannel = Channel<String>()
    override suspend fun setFriendAddRequest(flag: String, approve: Boolean, remark: String): ApiResponse<NullData> =
        returnChannel.getReturnValue(ApiBuilder(SetFriendAddRequest(flag, approve, remark)).build())

    override suspend fun setGroupAddRequest(
        flag: String,
        sub_type: String,
        approve: Boolean,
        reason: String
    ): ApiResponse<NullData> =
        returnChannel.getReturnValue(ApiBuilder(SetGroupAddRequest(flag, sub_type, approve, reason)).build())

    override suspend fun getBotInfo(): ApiResponse<GetLoginInfoResponse> =
        returnChannel.getReturnValue(ApiBuilder(GetLoginInfo()).build())

    override suspend fun getStrangerInfo(user_id: Long, noCache: Boolean): ApiResponse<GetStrangerInfoResponse> =
        returnChannel.getReturnValue(ApiBuilder(GetStrangerInfo(user_id, noCache)).build())

    override suspend fun getFriendList(): ApiResponse<Array<Friend>> =
        returnChannel.getReturnValue(ApiBuilder(GetFriendList()).build())

    override suspend fun deleteFriend(friend_id: Long): ApiResponse<NullData> =
        returnChannel.getReturnValue(ApiBuilder(DeleteFriend(friend_id)).build())

    override suspend fun getGroupInfo(group_id: Long, noCache: Boolean): ApiResponse<GetGroupInfoResponse> =
        returnChannel.getReturnValue(ApiBuilder(GetGroupInfo(group_id, noCache)).build())

    override suspend fun getGroupInfoList(): ApiResponse<List<GetGroupInfoResponse>> =
        returnChannel.getReturnValue(ApiBuilder(GetGroupList()).build())

    override suspend fun canSendImage(): ApiResponse<CanSendImageResponse> =
        returnChannel.getReturnValue(ApiBuilder(CanSendImage()).build())

    override suspend fun canSendRecord(): ApiResponse<CanSendRecordResponse> =
        returnChannel.getReturnValue(ApiBuilder(CanSendRecord()).build())

    override suspend fun getVersionInfo(): ApiResponse<GetVersionInfoResponse> =
        returnChannel.getReturnValue(ApiBuilder(GetVersionInfo()).build())

    override suspend fun setGoCqhttpRestart(delay: Long): ApiResponse<NullData> =
        returnChannel.getReturnValue(ApiBuilder(SetRestart(delay)).build())

    override suspend fun ocrImage(image_id: String): ApiResponse<OcrImageResponse> =
        returnChannel.getReturnValue(ApiBuilder(OcrImage(image_id)).build())

    override suspend fun getStatus(): ApiResponse<GetStatusResponse> =
        returnChannel.getReturnValue(ApiBuilder(GetStatus()).build())

    override suspend fun reloadEventFilter(file: String): ApiResponse<NullData> =
        returnChannel.getReturnValue(ApiBuilder(ReloadEventFilter(file)).build())

    override suspend fun downloadFile(
        url: String,
        thread_count: Int,
        headers: List<String>
    ): ApiResponse<DownloadFileResponse> =
        returnChannel.getReturnValue(ApiBuilder(DownloadFile(url, thread_count, headers)).build())

    override suspend fun getOnlineClients(noCache: Boolean): ApiResponse<GetOnlineClientsResponse> =
        returnChannel.getReturnValue(ApiBuilder(GetOnlineClients(noCache)).build())

    override suspend fun setEssenceMsg(message_id: Int): ApiResponse<NullData> =
        returnChannel.getReturnValue(ApiBuilder(SetEssenceMsg(message_id)).build())

    override suspend fun deleteEssenceMsg(message_id: Int): ApiResponse<NullData> =
        returnChannel.getReturnValue(ApiBuilder(DeleteEssenceMsg(message_id)).build())

    override suspend fun checkUrlSafely(url: String): ApiResponse<CheckUrlSafelyResponse> =
        returnChannel.getReturnValue(ApiBuilder(CheckUrlSafely(url)).build())

    override suspend fun getModelShow(model: String): ApiResponse<GetModelShowResponse> =
        returnChannel.getReturnValue(ApiBuilder(GetModelShow(model)).build())

    override suspend fun setModelShow(model: String, model_show: String): ApiResponse<NullData> =
        returnChannel.getReturnValue(ApiBuilder(SetModelShow(model, model_show)).build())

    override suspend fun getGroupSystemMsg(): ApiResponse<GetGroupSystemMsgResponse> =
        returnChannel.getReturnValue(ApiBuilder(GetGroupSystemMsg()).build())

    override suspend fun getMsg(message_id: Int): ApiResponse<GetMsgResponse> =
        returnChannel.getReturnValue(ApiBuilder(GetMsg(message_id)).build())

    override suspend fun getForwardMsg(message_id: Int): ApiResponse<List<GetForwardMsgResponse>> =
        returnChannel.getReturnValue(ApiBuilder(GetForwardMsg(message_id.toString())).build())

    override suspend fun getImage(file: String): ApiResponse<GetImageResponse> =
        returnChannel.getReturnValue(ApiBuilder(GetImage(file)).build())

    override suspend fun getFriendById(user_id: Long): User = User(user_id)

    @Throws(FriendNotFoundException::class)
    override suspend fun getFriendByIdSafely(user_id: Long): User {
        for (friend in getFriendList().data!!) {
            if (friend.user_id == user_id) return User(user_id)
        }
        throw FriendNotFoundException()
    }

    override suspend fun getGroupById(group_id: Long): Group = Group(group_id)

    @Throws(GroupNotFoundException::class)
    override suspend fun getGroupByIdSafely(group_id: Long): Group {
        for (group in getGroupInfoList().data!!) {
            if (group.group_id == group_id) return Group(group_id)
        }
        throw GroupNotFoundException()
    }

    override suspend fun getGroupUserById(user_id: Long, group_id: Long): GroupUser = GroupUser(user_id, group_id)

    @Throws(GroupUserNotFoundException::class)
    override suspend fun getGroupUserByIdSafely(user_id: Long, group_id: Long): GroupUser {
        val group = getGroupByIdSafely(group_id)
        for (member in group.getGroupMember().data!!) {
            if (member.userId == user_id) return GroupUser(user_id, group_id)
        }
        throw GroupUserNotFoundException()
    }
}