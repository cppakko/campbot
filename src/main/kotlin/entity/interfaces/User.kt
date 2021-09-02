package entity.interfaces

import data.api.ApiResponse
import data.api.GetVipInfoResponse
import data.api.NullData
import data.api.SendPrivateMsgResponse

interface UserInterface {
    val userId: Long

    suspend fun sendPrivateMsg(msg: String): ApiResponse<SendPrivateMsgResponse>
    suspend fun getVipInfo(): ApiResponse<GetVipInfoResponse>
}

interface GroupUserInterface : UserInterface {
    val groupId: Long

    suspend fun kickFromGroup(): ApiResponse<NullData>
    suspend fun groupBan(duration: Long): ApiResponse<NullData>
}