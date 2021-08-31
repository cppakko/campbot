package entity.interfaces

import Bot
import data.api.*
import entity.GroupUser
import entity.User

interface GroupInterface {
    val groupId: Long
    val bot: Bot

    fun getGroupMember(): List<User>
    suspend fun sendGroupMsg(msg: String): SendPrivateMsgResponse
    fun setGroupKick(user_id: Long)
    fun setGroupAnonymousBan(flag: String,duration: Long)
    fun setGroupAdmin(user_id: Long,enable: Boolean)
    fun setGroupCard(user_id: Long,card: String)
    fun setGroupName(name: String)
    fun setGroupLeave(is_dismiss: Boolean = false)
    fun setGroupSpecialTitle(user_id: Long,specialTitle: String,duration: Long)
    fun getGroupMemberInfo(user_id: Long,noCache: Boolean = false)
    fun getGroupMemberList(): List<GroupUser>
    fun getGroupHonor(type: String): GetGroupHonorInfoResponse
    fun setGroupPortrait(file: String,cache: Int)
    fun getGroupSystemMsg(): GetGroupSystemMsgResponse
    fun uploadGroupFile(file: String,name: String,folder: String)
    fun getGroupRootFiles(): GetGroupRootFilesResponse
    fun getGroupFilesByFolder(folder_id: String): GetGroupFilesByFolderResponse
    fun getGroupFileUrl(file_id: String,busid: Int): GetGroupFileUrlResponse
    fun getGroupAtAllRemain(): GetGroupAtAllRemainResponse
    fun sendGroupNotice(content: String)
    fun getGroupMsgHistory(message_seq: Long): GetGroupMsgHistoryResponse
    fun getEssenceMsgList(): GetEssenceMsgListResponse
}