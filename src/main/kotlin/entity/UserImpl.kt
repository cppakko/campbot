package entity

import data.api.GetVersionInfoResponse
import data.api.SendPrivateMsgResponse
import entity.interfaces.GroupUserInterface
import entity.interfaces.UserInterface

class User(override val userId: Long) : UserInterface {
    override fun sendPrivateMsg(msg: String): SendPrivateMsgResponse {
        TODO("Not yet implemented")
    }

    override fun getVipInfo(): GetVersionInfoResponse {
        TODO("Not yet implemented")
    }
}

class GroupUser(override val userId: Long) : GroupUserInterface {
    override fun kickFromGroup() {
        TODO("Not yet implemented")
    }

    override fun groupBan(duration: Long) {
        TODO("Not yet implemented")
    }

    override fun sendPrivateMsg(msg: String): SendPrivateMsgResponse {
        TODO("Not yet implemented")
    }

    override fun getVipInfo(): GetVersionInfoResponse {
        TODO("Not yet implemented")
    }

}