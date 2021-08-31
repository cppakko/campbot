package entity

import Bot
import data.api.*
import entity.interfaces.GroupInterface
import network.websocket.WsInit

class Group(override val groupId: Long, override val bot: Bot) : GroupInterface {
    override fun getGroupMember(): List<User> {
        TODO("Not yet implemented")
    }

    override suspend fun sendGroupMsg(msg: String): SendPrivateMsgResponse {
        WsInit.callApiChannel.send(msg)
        //TODO Not yet implemented
        return SendPrivateMsgResponse(123)
    }

    override fun setGroupKick(user_id: Long) {
        TODO("Not yet implemented")
    }

    override fun setGroupAnonymousBan(flag: String, duration: Long) {
        TODO("Not yet implemented")
    }

    override fun setGroupAdmin(user_id: Long, enable: Boolean) {
        TODO("Not yet implemented")
    }

    override fun setGroupCard(user_id: Long, card: String) {
        TODO("Not yet implemented")
    }

    override fun setGroupName(name: String) {
        TODO("Not yet implemented")
    }

    override fun setGroupLeave(is_dismiss: Boolean) {
        TODO("Not yet implemented")
    }

    override fun setGroupSpecialTitle(user_id: Long, specialTitle: String, duration: Long) {
        TODO("Not yet implemented")
    }

    override fun getGroupMemberInfo(user_id: Long, noCache: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getGroupMemberList(): List<GroupUser> {
        TODO("Not yet implemented")
    }

    override fun getGroupHonor(type: String): GetGroupHonorInfoResponse {
        TODO("Not yet implemented")
    }

    override fun setGroupPortrait(file: String, cache: Int) {
        TODO("Not yet implemented")
    }

    override fun getGroupSystemMsg(): GetGroupSystemMsgResponse {
        TODO("Not yet implemented")
    }

    override fun uploadGroupFile(file: String, name: String, folder: String) {
        TODO("Not yet implemented")
    }

    override fun getGroupRootFiles(): GetGroupRootFilesResponse {
        TODO("Not yet implemented")
    }

    override fun getGroupFilesByFolder(folder_id: String): GetGroupFilesByFolderResponse {
        TODO("Not yet implemented")
    }

    override fun getGroupFileUrl(file_id: String, busid: Int): GetGroupFileUrlResponse {
        TODO("Not yet implemented")
    }

    override fun getGroupAtAllRemain(): GetGroupAtAllRemainResponse {
        TODO("Not yet implemented")
    }

    override fun sendGroupNotice(content: String) {
        TODO("Not yet implemented")
    }

    override fun getGroupMsgHistory(message_seq: Long): GetGroupMsgHistoryResponse {
        TODO("Not yet implemented")
    }

    override fun getEssenceMsgList(): GetEssenceMsgListResponse {
        TODO("Not yet implemented")
    }
}