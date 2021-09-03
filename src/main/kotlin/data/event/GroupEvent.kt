package data.event

import api.ApiBuilder
import data.api.ApiResponse
import data.api.HandleQuickOperation
import data.api.NullData
import kotlinx.coroutines.channels.Channel
import listener.GroupEvent
import utils.getReturnValue

data class GroupFileUpload(
    val `file`: File,
    val group_id: Long,
    val notice_type: String,
    val post_type: String,
    val self_id: Long,
    val time: Long,
    val user_id: Long
) : GroupEvent {
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
) : GroupEvent

data class GroupAdminSetChange(
    val data: GroupAdminChange
) : GroupEvent

data class GroupAdminUnSetChange(
    val data: GroupAdminChange
) : GroupEvent

data class GroupDecreaseOrIncrease(
    val time: Long,
    val self_id: Long,
    val post_type: String,
    val notice_type: String,
    val sub_type: String,
    val group_id: Long,
    val operator_id: Long,
    val user_id: Long
) : GroupEvent

data class GroupMemberLeave(
    val data: GroupDecreaseOrIncrease
) : GroupEvent

data class GroupMemberKick(
    val data: GroupDecreaseOrIncrease
) : GroupEvent

data class GroupKickMe(
    val data: GroupDecreaseOrIncrease
) : GroupEvent

data class GroupMemberApprove(
    val data: GroupDecreaseOrIncrease
) : GroupEvent

data class GroupMemberInvite(
    val data: GroupDecreaseOrIncrease
) : GroupEvent

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
) : GroupEvent

data class GroupLiftBan (
    val data: GroupBan
    ): GroupEvent

data class GroupRecall(
    val group_id: Long,
    val message_id: Long,
    val notice_type: String,
    val operator_id: Long,
    val post_type: String,
    val self_id: Long,
    val time: Long,
    val user_id: Long
) : GroupEvent

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
) : GroupEvent

data class GroupLuckyKing(
    val post_type: String,
    val notice_type: String,
    val group_id: Long,
    val sub_type: String,
    val user_id: Long,
    val target_id: Long
) : GroupEvent

data class GroupHonor(
    val post_type: String,
    val notice_type: String,
    val group_id: Long,
    val sub_type: String,
    val user_id: Long,
    val honor_type: String
) : GroupEvent

data class TalkativeGroupHonor(
    val data: GroupHonor
): GroupEvent

data class PerformerGroupHonor(
    val data: GroupHonor
): GroupEvent

data class EmotionGroupHonor(
    val data: GroupHonor
): GroupEvent

data class GroupCardUpdate(
    val post_type: String,
    val notice_type: String,
    val group_id: Long,
    val user_id: Long,
    val card_new: String,
    val card_old: String
) : GroupEvent

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
) : GroupEvent {
    suspend fun accept(isAccept: Boolean, remark: String): ApiResponse<NullData> = Channel<String>().getReturnValue(
        ApiBuilder(
            HandleQuickOperation(this, ApproveData(isAccept, remark))
        ).build()
    )

    private data class ApproveData(
        val approve: Boolean,
        val remark: String
    )
}

data class GroupAddRequestEvent(
    val data: GroupAddOrInviteRequestEvent
): GroupEvent

data class GroupInviteRequestEvent(
    val data: GroupAddOrInviteRequestEvent
): GroupEvent
