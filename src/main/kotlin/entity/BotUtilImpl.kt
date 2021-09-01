package entity

import Bot
import api.ApiBuilder
import com.fasterxml.jackson.databind.ObjectMapper
import data.api.*
import entity.interfaces.BotUtil
import exceptions.GroupNotFoundException
import kotlinx.coroutines.channels.Channel
import network.websocket.WsInit
import utils.asJsonObject

class BotUtilImpl(val bot: Bot) : BotUtil {
    override fun setFriendAddRequest(flag: String, approve: Boolean, remark: String) {
        TODO("Not yet implemented")
    }

    override fun setGroupAddRequest(flag: String, sub_type: String, approve: Boolean, reason: String) {
        TODO("Not yet implemented")
    }

    override fun getBotInfo(): GetLoginInfoResponse {
        TODO("Not yet implemented")
    }

    override fun getStrangerInfo(user_id: Long, noCache: Boolean): GetStrangerInfoResponse {
        TODO("Not yet implemented")
    }

    override fun getFriendList(): Array<User> {
        TODO("Not yet implemented")
    }

    override fun deleteFriend(friend_id: Long) {
        TODO("Not yet implemented")
    }

    override fun getGroupInfo(group_id: Long, noCache: Boolean): GetGroupInfoResponse {
        TODO("Not yet implemented")
    }

    override suspend fun reflashGroupList(): MutableList<Group> {
        val returnChannel = Channel<String>()
        val api = ApiBuilder(GetGroupList()).build()
        WsInit.callApiChannel.send(Pair(api, returnChannel))
        for (str in returnChannel) {
            returnChannel.close()
            val list = ObjectMapper().readTree(str).get("data").toString().asJsonObject<Array<GetGroupInfoResponse>>()
            if (!bot.isGroupListInitialized()) {
                bot.groupList = mutableListOf()
            } else {
                bot.groupList.clear()
            }
            list.forEach { groupInfo ->
                bot.groupList.add(Group(groupInfo.group_id, bot))
            }
        }
        return bot.groupList
    }

    override fun canSendImage(): Boolean {
        TODO("Not yet implemented")
    }

    override fun canSendRecord(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getVersionInfo(): GetVersionInfoResponse {
        TODO("Not yet implemented")
    }

    override fun setGoCqhttpRestart(delay: Long) {
        TODO("Not yet implemented")
    }

    override fun ocrImage(image_id: String): OcrImageResponse {
        TODO("Not yet implemented")
    }

    override fun getStatus(): GetStatusResponse {
        TODO("Not yet implemented")
    }

    override fun reloadEventFilter(file: String) {
        TODO("Not yet implemented")
    }

    override fun downloadFile(url: String, thread_count: Int, headers: String) {
        TODO("Not yet implemented")
    }

    override fun downloadFile(url: String, thread_count: Int, headers: Array<String>) {
        TODO("Not yet implemented")
    }

    override fun getOnlineClients(noCache: Boolean): GetOnlineClientsResponse {
        TODO("Not yet implemented")
    }

    override fun setEssenceMsg(message_id: Int) {
        TODO("Not yet implemented")
    }

    override fun deleteEssenceMsg(message_id: Int) {
        TODO("Not yet implemented")
    }

    override fun checkUrlSafely(url: String): CheckUrlSafelyResponse {
        TODO("Not yet implemented")
    }

    override fun getModelShow(model: String): GetModelShowResponse {
        TODO("Not yet implemented")
    }

    override fun setModelShow(model: String, model_show: String) {
        TODO("Not yet implemented")
    }

    override fun getUserById(user_id: Long): User {
        TODO("Not yet implemented")
    }

    override fun getGroupById(group_id: Long): Group {
        return Group(group_id, bot)
    }

    override suspend fun getGroupByIdSafely(group_id: Long): Group {
        if (!(bot.isGroupListInitialized())) {
            bot.utils.reflashGroupList()
        }
        for (group in bot.groupList) {
            if (group.groupId == group_id) return Group(group_id, bot)
        }
        throw GroupNotFoundException()
    }

    override fun getGroupUserById(user_id: Long, group_id: Long): GroupUser {
        TODO("Not yet implemented")
    }
}