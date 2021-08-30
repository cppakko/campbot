package data.api

import utils.ApiEndPoint

@ApiEndPoint("set_group_kick")
data class SetGroupKick(
    val group_id: Long,
    val user_id: Long,
    val reject_add_request: Boolean = false
)

@ApiEndPoint("set_group_ban")
data class SetGroupBan(
    val group_id: Long,
    val user_id: Long,
    val duration: Long
)

//TODO 添加可选支持
@ApiEndPoint("set_group_anonymous_ban")
data class SetGroupAnonymousBan(
    val group_id: Long,
    val anonymous: Anonymous,
    val flag: String,
    val duration: Long
) {
    data class Anonymous(
        val id: Long,
        val name: String,
        val flag: String
    )
}

@ApiEndPoint("set_group_whole_ban")
data class SetGroupWholeBan(
    val group_id: Long,
    val enable: Boolean = true
)

@ApiEndPoint("set_group_admin")
data class SetGroupAdmin(
    val group_id: Long,
    val user_id: Long,
    val enable: Boolean = true
)

@ApiEndPoint("set_group_anonymous")
data class SetGroupAnonymous(
    val group_id: Long,
    val enable: Boolean
)

@ApiEndPoint("set_group_card")
data class SetGroupCard(
    val group_id: Long,
    val user_id: Long,
    val card: String
)

@ApiEndPoint("set_group_name")
data class SetGroupName(
    val group_id: Long,
    val group_name: String
)

@ApiEndPoint("set_group_leave")
data class SetGroupLeave(
    val group_id: Long,
    val is_dismiss: Boolean = false
)

@ApiEndPoint("set_group_special_title")
data class SetGroupSpecialTitle(
    val group_id: Long,
    val user_id: Long,
    val special_title: String = "",
    val duration: Int = -1
)

