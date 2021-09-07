package data.event

enum class EventEnum(val post_type: String) {
    MESSAGE_EVENT("message"),
    NOTICE_EVENT("notice"),
    REQUEST_EVENT("request"),
    META_EVENT("meta_event");

    companion object {
        fun from(findValue: String): EventEnum = values().first { it.post_type == findValue }
    }
}

enum class MessageType(val message_type: String) {
    PRIVATE("private"),
    GROUP("group");

    companion object {
        fun from(findValue: String): MessageType = values().first { it.message_type == findValue }
    }
}

enum class MetaEvent(val meta_event_type: String) {
    LIFECYCLE("lifecycle"),
    HEARTBEAT("heartbeat");

    companion object {
        fun from(findValue: String): MetaEvent = values().first { it.meta_event_type == findValue }
    }
}

enum class NoticeEnum(val notice_type: String) {
    GROUP_UPLOAD_NOTICE("group_upload"),
    GROUP_ADMIN_NOTICE("group_admin"),
    GROUP_DECREASE("group_decrease"),
    GROUP_INCREASE("group_increase"),
    GROUP_BAN("group_ban"),
    FRIEND_ADD("friend_add"),
    GROUP_RECALL("group_recall"),
    FRIEND_RECALL("friend_recall"),
    NOTIFY("notify"),
    GROUP_CARD("group_card"),
    OFFLINE_FILE("offline_file"),
    ESSENCE_MESSAGE("essence"),
    CLIENT_STATUS_CHANGE("client_status");

    companion object {
        fun from(findValue: String): NoticeEnum = values().first { it.notice_type == findValue }
    }
}

enum class SubTypeEnum(val sub_type: String) {
    FRIEND_MESSAGE("friend"),
    GROUP_TEMP_MESSAGE("group"),
    GROUP_SELF_TEMP_MESSAGE("group_self"),
    OTHER_PRIVATE_MESSAGE("other"),
    NORMAL_GROUP_MESSAGE("normal"),
    ANONYMOUS_GROUP_MESSAGE("anonymous"),
    NOTICE_GROUP_MESSAGE("notice"),
    SET_GROUP_ADMIN("set"),
    UNSET_GROUP_ADMIN("unset"),
    GROUP_LEAVE("leave"),
    GROUP_KICK("kick"),
    GROUP_KICK_ME("kick_me"),
    GROUP_APPROVE("approve"),
    GROUP_INVITE("invite"),
    GROUP_BAN_BAN("ban"),
    GROUP_BAN_LIFT_BAN("lift_ban"),
    POKE("poke"),
    LUCKY_KING("lucky_king"),
    HONOR("honor"),
    GROUP_ADD_REQUEST("add"),
    GROUP_INVITE_REQUEST("invite"),
    ESSENCE_ADD("add"),
    ESSENCE_DELETE("delete"),
    HTTP_POST_ENABLE("enable"),
    HTTP_POST_DISABLE("disable"),
    WEBSOCKET_CONNECT("connect");

    companion object {
        fun from(findValue: String): SubTypeEnum = values().first { it.sub_type == findValue }
    }
}

enum class HonorType(val honor_type: String) {
    HONOR_TALKATIVE("talkative:龙王"),
    HONOR_PERFORMER("performer:群聊之火"),
    HONOR_EMOTION("emotion:快乐源泉");

    companion object {
        fun from(findValue: String): HonorType = values().first { it.honor_type == findValue }
    }
}

enum class RequestType(val request_type: String) {
    FRIEND("friend"),
    GROUP("group");

    companion object {
        fun from(findValue: String): RequestType = values().first { it.request_type == findValue }
    }
}