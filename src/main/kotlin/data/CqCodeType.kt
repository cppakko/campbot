package data

enum class CqCodeType(val code: String) {
    TEXT("text"),
    FACE("face"),
    IMAGE("image"),
    RECORD("record"),
    VIDEO("video"),
    AT("at"),
    RPS("rps"),
    DICE("dice"),
    SHAKE("shake"),
    POKE("poke"),
    ANONYMOUS("anonymous"),
    SHARE("share"),
    CONTACT("contact"),
    LOCATION("location"),
    MUSIC("music"),
    REPLY("reply"),
    FORWARD("forward"),
    NODE("node"),
    XML("xml"),
    JSON("json")
}