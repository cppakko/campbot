package event

import Bot
import com.fasterxml.jackson.databind.ObjectMapper
import data.event.*
import data.event.EventEnum.*
import data.event.HonorType.*
import data.event.MessageType.GROUP
import data.event.MessageType.PRIVATE
import data.event.MetaEvent.HEARTBEAT
import data.event.MetaEvent.LIFECYCLE
import data.event.NoticeEnum.*
import data.event.RequestType.FRIEND
import data.event.SubTypeEnum.*
import kotlinx.coroutines.channels.Channel
import mu.KotlinLogging
import utils.*

internal class EventPasser(private val bot: Bot) {
    companion object {
        val channel = Channel<String>()
        val logger = KotlinLogging.logger {}
    }
    //TODO 调整日志输出
    //TODO 并发处理
    suspend fun run() {
        for (rowEventStr in channel) {
            logger.info { rowEventStr }
            when (rowEventStr.readPostType()) {
                META_EVENT -> when (rowEventStr.readMetaEventType()) {
                    HEARTBEAT -> {
                        val obj = rowEventStr.asJsonObject<HeartEvent>()
                        logger.info { "${obj.time} ♥跳了一下~" }
                    }
                    LIFECYCLE -> {
                        val obj = rowEventStr.asJsonObject<LifeCycleEvent>()
                        logger.info { "${obj.time} ${obj.self_id} 连接成功!" }
                    }
                }
                MESSAGE_EVENT -> when (rowEventStr.readMessageType()) {
                    PRIVATE -> when (rowEventStr.readSubType()) {
                        FRIEND_MESSAGE -> bot.eventManager.notify(FriendPrivateMsg(rowEventStr.asJsonObject()),rowEventStr)
                        GROUP_TEMP_MESSAGE -> bot.eventManager.notify(GroupPrivateMsg(rowEventStr.asJsonObject()),rowEventStr)
                        GROUP_SELF_TEMP_MESSAGE -> bot.eventManager.notify(GroupSelfPrivateMsg(rowEventStr.asJsonObject()),rowEventStr)
                        OTHER_PRIVATE_MESSAGE -> bot.eventManager.notify(OtherPrivateMsg(rowEventStr.asJsonObject()),rowEventStr)
                        else -> logger.warn {
                            "发生了未知的解析错误 位于 MESSAGE_EVENT -> " +
                                    "MESSAGE_EVENT -> eventStr.readSubType()\n原文 => $rowEventStr"
                        }
                    }
                    GROUP -> {
                        when (rowEventStr.readSubType()) {
                            NORMAL_GROUP_MESSAGE -> bot.eventManager.notify(NormalGroupMsg(rowEventStr.asJsonObject()),rowEventStr)
                            ANONYMOUS_GROUP_MESSAGE -> bot.eventManager.notify(AnonymousGroupMsg(rowEventStr.asJsonObject()),rowEventStr)
                            NOTICE_GROUP_MESSAGE -> bot.eventManager.notify(NoticeGroupMsg(rowEventStr.asJsonObject()),rowEventStr)
                            else -> logger.warn {
                                "发生了未知的解析错误 位于 MESSAGE_EVENT -> " +
                                        "GROUP -> eventStr.readSubType()\n" +
                                        "原文 => $rowEventStr"
                            }
                        }
                    }
                }

                NOTICE_EVENT -> when (rowEventStr.readNoticeType()) {
                    GROUP_UPLOAD_NOTICE -> {
                        rowEventStr.asJsonObject<GroupFileUpload>()
                    }
                    GROUP_ADMIN_NOTICE -> {
                        when (rowEventStr.readSubType()) {
                            SET_GROUP_ADMIN -> bot.eventManager.notify(GroupAdminSetChange(rowEventStr.asJsonObject()),rowEventStr)
                            UNSET_GROUP_ADMIN -> bot.eventManager.notify(GroupAdminUnSetChange(rowEventStr.asJsonObject()),rowEventStr)
                            else -> logger.warn {
                                "发生了未知的解析错误 位于 NOTICE_EVENT -> " +
                                        "GROUP_ADMIN_NOTICE -> eventStr.readSubType()\n" +
                                        "原文 => $rowEventStr"
                            }
                        }
                    }
                    GROUP_DECREASE, GROUP_INCREASE -> {
                        when (rowEventStr.readSubType()) {
                            GROUP_LEAVE -> bot.eventManager.notify(GroupMemberLeave(rowEventStr.asJsonObject()),rowEventStr)
                            GROUP_KICK -> bot.eventManager.notify(GroupMemberKick(rowEventStr.asJsonObject()),rowEventStr)
                            GROUP_KICK_ME -> bot.eventManager.notify(GroupKickMe(rowEventStr.asJsonObject()),rowEventStr)
                            GROUP_APPROVE -> bot.eventManager.notify(GroupMemberApprove(rowEventStr.asJsonObject()),rowEventStr)
                            GROUP_INVITE -> bot.eventManager.notify(GroupMemberInvite(rowEventStr.asJsonObject()),rowEventStr)
                            else -> logger.warn {
                                "发生了未知的解析错误 位于 NOTICE_EVENT -> " +
                                        "GROUP_GROUP_DECREASE,GROUP_INCREASE -> eventStr.readSubType()\n" +
                                        "原文 => $rowEventStr"
                            }
                        }
                    }
                    GROUP_BAN -> {
                        when (rowEventStr.readSubType()) {
                            GROUP_BAN_BAN -> bot.eventManager.notify(rowEventStr.asJsonObject<GroupBan>(),rowEventStr)
                            GROUP_BAN_LIFT_BAN -> bot.eventManager.notify(GroupLiftBan(rowEventStr.asJsonObject()),rowEventStr)
                            else -> logger.warn {
                                "发生了未知的解析错误 位于 NOTICE_EVENT -> " +
                                        "GROUP_BAN -> eventStr.readSubType()\n" +
                                        "原文 => $rowEventStr"
                            }
                        }
                    }
                    FRIEND_ADD -> {
                        bot.eventManager.notify(rowEventStr.asJsonObject<FriendAdd>())
                    }
                    GROUP_RECALL -> {
                        bot.eventManager.notify(rowEventStr.asJsonObject<GroupRecall>(),rowEventStr)
                    }
                    FRIEND_RECALL -> {
                        bot.eventManager.notify(rowEventStr.asJsonObject<FriendRecall>(),rowEventStr)
                    }
                    NOTIFY -> {
                        when (rowEventStr.readSubType()) {
                            POKE -> {
                                if (ObjectMapper()
                                        .readTree(rowEventStr)
                                        .get("group_id") != null
                                ) {
                                    bot.eventManager.notify(rowEventStr.asJsonObject<GroupPoke>(),rowEventStr)
                                } else {
                                    bot.eventManager.notify(rowEventStr.asJsonObject<FriendPoke>(),rowEventStr)
                                }
                            }
                            LUCKY_KING -> {
                                bot.eventManager.notify(rowEventStr.asJsonObject<GroupLuckyKing>(),rowEventStr)
                            }
                            HONOR -> {
                                when (rowEventStr.readHonorType()) {
                                    HONOR_TALKATIVE -> bot.eventManager.notify(TalkativeGroupHonor(rowEventStr.asJsonObject()),rowEventStr)
                                    HONOR_PERFORMER -> bot.eventManager.notify(PerformerGroupHonor(rowEventStr.asJsonObject()),rowEventStr)
                                    HONOR_EMOTION -> bot.eventManager.notify(EmotionGroupHonor(rowEventStr.asJsonObject()),rowEventStr)
                                }
                            }
                            else -> logger.warn {
                                "发生了未知的解析错误 位于 NOTICE_EVENT -> " +
                                        "NOTIFY -> eventStr.readSubType()\n" +
                                        "原文 => $rowEventStr"
                            }
                        }
                    }
                    GROUP_CARD -> {
                        bot.eventManager.notify(rowEventStr.asJsonObject<GroupCardUpdate>(),rowEventStr)
                    }
                    OFFLINE_FILE -> {
                        bot.eventManager.notify(rowEventStr.asJsonObject<OfflineFile>(),rowEventStr)
                    }
                    ESSENCE_MESSAGE -> {
                        when (rowEventStr.readSubType()) {
                            ESSENCE_ADD -> bot.eventManager.notify(AddEssenceEvent(rowEventStr.asJsonObject()))
                            ESSENCE_DELETE -> bot.eventManager.notify(DeleteEssenceEvent(rowEventStr.asJsonObject()))
                            else -> logger.warn {
                                "发生了未知的解析错误 位于 NOTICE_EVENT -> " +
                                        "ESSENCE_MESSAGE -> eventStr.readSubType()\n" +
                                        "原文 => $rowEventStr"
                            }
                        }
                    }
                    CLIENT_STATUS_CHANGE -> {
                        bot.eventManager.notify(rowEventStr.asJsonObject<OtherClientStatusChangeEvent>())
                    }

                }
                REQUEST_EVENT -> when (rowEventStr.readRequestType()) {
                    FRIEND -> {
                        bot.eventManager.notify(rowEventStr.asJsonObject<FriendAddRequest>())
                    }
                    RequestType.GROUP -> {
                        when (rowEventStr.readSubType()) {
                            GROUP_ADD_REQUEST -> GroupAddRequestEvent(rowEventStr.asJsonObject())
                            GROUP_INVITE_REQUEST -> GroupInviteRequestEvent(rowEventStr.asJsonObject())
                            else -> logger.warn {
                                "发生了未知的解析错误 位于 REQUEST_EVENT -> " +
                                        "eventStr.readSubType()\n" +
                                        "原文 => $rowEventStr"
                            }
                        }
                    }
                }
            }
        }
    }
}