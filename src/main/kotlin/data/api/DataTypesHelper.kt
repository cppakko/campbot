package data.api

data class MessageNode(
    val id: Int,
    val name: String,
    val uin: Long,
    val content: String,
    val seq: String
)