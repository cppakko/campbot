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
    override suspend fun setFriendAddRequest(flag: String, approve: Boolean, remark: String): ApiResponse<NullData> =
        Channel<String>().getReturnValue(ApiBuilder(SetFriendAddRequest(flag, approve, remark)).build())

    override suspend fun setGroupAddRequest(
        flag: String,
        sub_type: String,
        approve: Boolean,
        reason: String
    ): ApiResponse<NullData> =
        Channel<String>().getReturnValue(ApiBuilder(SetGroupAddRequest(flag, sub_type, approve, reason)).build())

    override suspend fun getBotInfo(): ApiResponse<GetLoginInfoResponse> =
        Channel<String>().getReturnValue(ApiBuilder(GetLoginInfo()).build())

    override suspend fun getStrangerInfo(user_id: Long, noCache: Boolean): ApiResponse<GetStrangerInfoResponse> =
        Channel<String>().getReturnValue(ApiBuilder(GetStrangerInfo(user_id, noCache)).build())

    override suspend fun getFriendList(): ApiResponse<Array<Friend>> =
        Channel<String>().getReturnValue(ApiBuilder(GetFriendList()).build())

    override suspend fun deleteFriend(friend_id: Long): ApiResponse<NullData> =
        Channel<String>().getReturnValue(ApiBuilder(DeleteFriend(friend_id)).build())

    override suspend fun getGroupInfo(group_id: Long, noCache: Boolean): ApiResponse<GetGroupInfoResponse> =
        Channel<String>().getReturnValue(ApiBuilder(GetGroupInfo(group_id, noCache)).build())

    override suspend fun getGroupInfoList(): ApiResponse<List<GetGroupInfoResponse>> =
        Channel<String>().getReturnValue(ApiBuilder(GetGroupList()).build())

    override suspend fun canSendImage(): ApiResponse<CanSendImageResponse> =
        Channel<String>().getReturnValue(ApiBuilder(CanSendImage()).build())

    override suspend fun canSendRecord(): ApiResponse<CanSendRecordResponse> =
        Channel<String>().getReturnValue(ApiBuilder(CanSendRecord()).build())

    override suspend fun getVersionInfo(): ApiResponse<GetVersionInfoResponse> =
        Channel<String>().getReturnValue(ApiBuilder(GetVersionInfo()).build())

    override suspend fun setGoCqhttpRestart(delay: Long): ApiResponse<NullData> =
        Channel<String>().getReturnValue(ApiBuilder(SetRestart(delay)).build())

    override suspend fun ocrImage(image_id: String): ApiResponse<OcrImageResponse> =
        Channel<String>().getReturnValue(ApiBuilder(OcrImage(image_id)).build())

    override suspend fun getStatus(): ApiResponse<GetStatusResponse> =
        Channel<String>().getReturnValue(ApiBuilder(GetStatus()).build())

    override suspend fun reloadEventFilter(file: String): ApiResponse<NullData> =
        Channel<String>().getReturnValue(ApiBuilder(ReloadEventFilter(file)).build())

    override suspend fun downloadFile(
        url: String, thread_count: Int, headers: List<String>
    ): ApiResponse<DownloadFileResponse> =
        Channel<String>().getReturnValue(ApiBuilder(DownloadFile(url, thread_count, headers)).build())

    override suspend fun getOnlineClients(noCache: Boolean): ApiResponse<GetOnlineClientsResponse> =
        Channel<String>().getReturnValue(ApiBuilder(GetOnlineClients(noCache)).build())

    override suspend fun setEssenceMsg(message_id: Int): ApiResponse<NullData> =
        Channel<String>().getReturnValue(ApiBuilder(SetEssenceMsg(message_id)).build())

    override suspend fun deleteEssenceMsg(message_id: Int): ApiResponse<NullData> =
        Channel<String>().getReturnValue(ApiBuilder(DeleteEssenceMsg(message_id)).build())

    override suspend fun checkUrlSafely(url: String): ApiResponse<CheckUrlSafelyResponse> =
        Channel<String>().getReturnValue(ApiBuilder(CheckUrlSafely(url)).build())

    override suspend fun getModelShow(model: String): ApiResponse<GetModelShowResponse> =
        Channel<String>().getReturnValue(ApiBuilder(GetModelShow(model)).build())

    override suspend fun setModelShow(model: String, model_show: String): ApiResponse<NullData> =
        Channel<String>().getReturnValue(ApiBuilder(SetModelShow(model, model_show)).build())

    override suspend fun getGroupSystemMsg(): ApiResponse<GetGroupSystemMsgResponse> =
        Channel<String>().getReturnValue(ApiBuilder(GetGroupSystemMsg()).build())

    override suspend fun getMsg(message_id: Int): ApiResponse<GetMsgResponse> =
        Channel<String>().getReturnValue(ApiBuilder(GetMsg(message_id)).build())

    override suspend fun getForwardMsg(message_id: String): ApiResponse<List<GetForwardMsgResponse>> =
        Channel<String>().getReturnValue(ApiBuilder(GetForwardMsg(message_id.toString())).build())

    override suspend fun getImage(file: String): ApiResponse<GetImageResponse> =
        Channel<String>().getReturnValue(ApiBuilder(GetImage(file)).build())

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
        val list = getGroupInfoList()
        for (group in list.data!!) {
            if (group.group_id == group_id) return Group(group_id)
        }
        throw GroupNotFoundException()
    }

    override suspend fun getGroupUserById(user_id: Long, group_id: Long): GroupUser = GroupUser(user_id, group_id)

    @Throws(GroupUserNotFoundException::class)
    override suspend fun getGroupUserByIdSafely(user_id: Long, group_id: Long): GroupUser {
        val group = getGroupByIdSafely(group_id)
        for (member in group.getGroupMemberInfoList().data!!) {
            if (member.user_id == user_id) return GroupUser(user_id, group_id)
        }
        throw GroupUserNotFoundException()
    }
}