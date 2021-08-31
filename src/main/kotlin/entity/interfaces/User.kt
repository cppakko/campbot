package entity.interfaces

import data.api.GetVersionInfoResponse
import data.api.SendPrivateMsgResponse

interface UserInterface {
    val userId: Long

    fun sendPrivateMsg(msg: String): SendPrivateMsgResponse
    fun getVipInfo(): GetVersionInfoResponse
}

interface GroupUserInterface : UserInterface {
    fun kickFromGroup()
    fun groupBan(duration: Long)
}