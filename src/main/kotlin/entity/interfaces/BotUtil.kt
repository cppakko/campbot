package entity.interfaces

import data.api.*
import entity.Group
import entity.GroupUser
import entity.User

interface BotUtil {
    fun setFriendAddRequest(flag: String,approve: Boolean,remark: String = "")
    fun setGroupAddRequest(flag: String,sub_type: String,approve: Boolean = true,reason: String)
    fun getBotInfo(): GetLoginInfoResponse
    fun getStrangerInfo(user_id: Long,noCache: Boolean = false): GetStrangerInfoResponse
    fun getFriendList(): Array<User>
    fun deleteFriend(friend_id: Long)
    fun getGroupInfo(group_id: Long,noCache: Boolean = false): GetGroupInfoResponse
    fun getGroupList(): Array<Group>
    fun canSendImage(): Boolean
    fun canSendRecord(): Boolean
    fun getVersionInfo(): GetVersionInfoResponse
    fun setGoCqhttpRestart(delay: Long)
    fun ocrImage(image_id: String): OcrImageResponse
    fun getStatus(): GetStatusResponse
    fun reloadEventFilter(file: String)
    fun downloadFile(url: String,thread_count: Int,headers: String)
    fun downloadFile(url: String,thread_count: Int,headers: Array<String>)
    fun getOnlineClients(noCache: Boolean): GetOnlineClientsResponse
    fun setEssenceMsg(message_id: Int)
    fun deleteEssenceMsg(message_id: Int)
    fun checkUrlSafely(url: String): CheckUrlSafelyResponse
    fun getModelShow(model: String): GetModelShowResponse
    fun setModelShow(model: String,model_show: String)

    fun getUserById(user_id: Long): User
    fun getGroupById(group_id: Long): Group
    fun getGroupUserById(user_id: Long,group_id: Long): GroupUser

}