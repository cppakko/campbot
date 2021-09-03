package entity

import api.ApiBuilder
import data.api.*
import entity.interfaces.GroupUserInterface
import entity.interfaces.UserInterface
import kotlinx.coroutines.channels.Channel
import utils.getReturnValue

class User(
    override val userId: Long
) : UserInterface {

    override suspend fun sendPrivateMsg(msg: String): ApiResponse<SendPrivateMsgResponse> =
        Channel<String>().getReturnValue(ApiBuilder(SendPrivateMsg(userId, msg)).build())

    override suspend fun getVipInfo(): ApiResponse<GetVipInfoResponse> =
        Channel<String>().getReturnValue(ApiBuilder(GetVipInfo(userId)).build())
}

class GroupUser(override val userId: Long, override val groupId: Long) : GroupUserInterface {
    override suspend fun kickFromGroup(): ApiResponse<NullData> =
        Channel<String>().getReturnValue(ApiBuilder(SetGroupKick(groupId, userId)).build())

    override suspend fun groupBan(duration: Long): ApiResponse<NullData> =
        Channel<String>().getReturnValue(ApiBuilder(SetGroupBan(groupId, userId, duration)).build())

    override suspend fun sendPrivateMsg(msg: String): ApiResponse<SendPrivateMsgResponse> =
        Channel<String>().getReturnValue(ApiBuilder(SendPrivateMsg(userId, msg)).build())

    override suspend fun getVipInfo(): ApiResponse<GetVipInfoResponse> =
        Channel<String>().getReturnValue(ApiBuilder(GetVipInfo(userId)).build())

}