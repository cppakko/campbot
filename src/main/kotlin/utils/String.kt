package utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import data.event.*

inline fun <reified T> String.asJsonObject() = jacksonObjectMapper().readValue<T>(this)

fun String.readPostType(): EventEnum = EventEnum.from(
    ObjectMapper()
        .readTree(this)
        .get("post_type")
        .asText()
)

fun String.readMetaEventType(): MetaEvent = MetaEvent.from(
    ObjectMapper()
        .readTree(this)
        .get("meta_event_type")
        .asText()
)

fun String.readSubType(): SubTypeEnum = SubTypeEnum.from(
    ObjectMapper()
        .readTree(this)
        .get("sub_type")
        .asText()
)

fun String.readMessageType(): MessageType = MessageType.from(
    ObjectMapper()
        .readTree(this)
        .get("message_type")
        .asText()
)

fun String.readNoticeType(): NoticeEnum = NoticeEnum.from(
    ObjectMapper()
        .readTree(this)
        .get("notice_type")
        .asText()
)

fun String.readRequestType(): RequestType = RequestType.from(
    ObjectMapper()
        .readTree(this)
        .get("request_type")
        .asText()
)

fun String.readHonorType(): HonorType = HonorType.from(
    ObjectMapper()
        .readTree(this)
        .get("honor_type")
        .asText()
)

fun String.readGroupId(): Long = ObjectMapper()
    .readTree(this)
    .get("group_id")
    .asLong()

fun String.readUserId(): Long = ObjectMapper()
    .readTree(this)
    .get("user_id")
    .asLong()