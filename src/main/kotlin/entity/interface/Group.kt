package entity.`interface`

interface Group {
    val groupId: Long
    val groupMember: List<User>

    fun sendGroupMsg(msg: String)
    fun setGroupKick(user_id: Long)
    //TODO ...
}