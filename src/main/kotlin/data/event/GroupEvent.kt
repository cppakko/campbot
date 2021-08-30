package data.event

import listener.Event

data class GroupFileUpload(
    val `file`: File,
    val group_id: Long,
    val notice_type: String,
    val post_type: String,
    val self_id: Long,
    val time: Long,
    val user_id: Long
) : Event {
    data class File(
        val busid: Long,
        val id: String,
        val name: String,
        val size: Long,
        val url: String
    )
}

data class GroupAdminChange(
    val group_id: Long,
    val notice_type: String,
    val post_type: String,
    val self_id: Long,
    val sub_type: String,
    val time: Long,
    val user_id: Long
) : Event

data class GroupAdminSetChange(
    val data: GroupAdminChange
) : Event

data class GroupAdminUnSetChange(
    val data: GroupAdminChange
) : Event

data class GroupDecreaseOrIncrease(
    val time: Long,
    val self_id: Long,
    val post_type: String,
    val notice_type: String,
    val sub_type: String,
    val group_id: Long,
    val operator_id: Long,
    val user_id: Long
) : Event

data class GroupMemberLeave(
    val data: GroupDecreaseOrIncrease
) : Event

data class GroupMemberKick(
    val data: GroupDecreaseOrIncrease
) : Event

data class GroupKickMe(
    val data: GroupDecreaseOrIncrease
) : Event

data class GroupMemberApprove(
    val data: GroupDecreaseOrIncrease
) : Event

data class GroupMemberInvite(
    val data: GroupDecreaseOrIncrease
) : Event

data class GroupBan(
    val duration: Long,
    val group_id: Long,
    val notice_type: String,
    val operator_id: Long,
    val post_type: String,
    val self_id: Long,
    val sub_type: String,
    val time: Long,
    val user_id: Long
) : Event

data class GroupLiftBan (
    val data: GroupBan
    ): Event

data class GroupRecall(
    val group_id: Long,
    val message_id: Long,
    val notice_type: String,
    val operator_id: Long,
    val post_type: String,
    val self_id: Long,
    val time: Long,
    val user_id: Long
) : Event

data class GroupPoke(
    val group_id: Long,
    val notice_type: String,
    val post_type: String,
    val self_id: Long,
    val sender_id: Long,
    val sub_type: String,
    val target_id: Long,
    val time: Long,
    val user_id: Long
) : Event

data class GroupLuckyKing(
    val post_type: String,
    val notice_type: String,
    val group_id: Long,
    val sub_type: String,
    val user_id: Long,
    val target_id: Long
) : Event

data class GroupHonor(
    val post_type: String,
    val notice_type: String,
    val group_id: Long,
    val sub_type: String,
    val user_id: Long,
    val honor_type: String
) : Event

data class TalkativeGroupHonor(
    val data: GroupHonor
): Event

data class PerformerGroupHonor(
    val data: GroupHonor
): Event

data class EmotionGroupHonor(
    val data: GroupHonor
): Event

data class GroupCardUpdate(
    val post_type: String,
    val notice_type: String,
    val group_id: Long,
    val user_id: Long,
    val card_new: String,
    val card_old: String
) : Event

data class GroupAddOrInviteRequestEvent(
    val time: Long,
    val self_id: Long,
    val post_type: String,
    val request_type: String,
    val sub_type: String,
    val group_id: Long,
    val user_id: Long,
    val comment: String,
    val flag: String
) : Event

data class GroupAddRequestEvent(
    val data: GroupAddOrInviteRequestEvent
): Event

data class GroupInviteRequestEvent(
    val data: GroupAddOrInviteRequestEvent
): Event
