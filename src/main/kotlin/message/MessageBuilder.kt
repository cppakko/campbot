@file:Suppress("unused")
package message

import com.fasterxml.jackson.annotation.JsonRawValue
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import data.*

//https://github.com/botuniverse/onebot/blob/master/v11/specs/message/segment.md
class MessageBuilder {
    private data class MessageArray(
        val type: String,
        @JsonRawValue val data: String
    )

    private val stringBuilder = StringBuilder()

    init {
        stringBuilder.append("[")
    }

    fun addText(text: String): MessageBuilder {
        addObject(Text(text), CqcodeType.TEXT)
        return this
    }

    fun addFace(faceId: Int): MessageBuilder {
        addObject(Face(faceId), CqcodeType.FACE)
        return this
    }

    //TODO
    fun addImage(file: String, type: String, cache: String = "1", id: String = "40000", c: Int = 3): MessageBuilder {
        addObject(Image(file, type, cache, id, c.toString()), CqcodeType.IMAGE)
        return this
    }

    fun addImage(file: String, cache: String = "1", id: String = "40000", c: Int = 3): MessageBuilder {
        addObject(ImageWithoutType(file, cache, id, c.toString()), CqcodeType.IMAGE)
        return this
    }

    fun addRecord(
        file: String,
        magic: Int = 0,
        cache: Int = 1,
        proxy: Int = 1,
        timeout: Long
    ): MessageBuilder {
        addObject(Record(file, magic, cache, proxy, timeout), CqcodeType.RECORD)
        return this
    }

    fun addVideo(file: String, cover: String, c: Int = 3): MessageBuilder {
        addObject(Video(file, cover, c), CqcodeType.VIDEO)
        return this
    }

    fun addAt(user_id: String, name: String): MessageBuilder {
        addObject(At(user_id, name), CqcodeType.AT)
        return this
    }

    /*    fun addRPS(): MessageBuilder {
            return this
        }
        fun addDice(): MessageBuilder {
            return this
        }
        fun addShake(): MessageBuilder {
            return this
        }*/
    fun addPoke(id: String): MessageBuilder {
        addObject(Poke(id), CqcodeType.POKE)
        return this
    }

    /*fun addAnonymous(ignore: String = "0"): MessageBuilder {
        addObject()
        return this
    }*/

    fun addShare(url: String, title: String, content: String, image: String): MessageBuilder {
        addObject(Share(url, title, content, image), CqcodeType.SHARE)
        return this
    }

    /*fun addFriendContact(type: String, id: String): MessageBuilder {
        addObject(FriendContact(type,id))
        return this
    }*/

    /*fun addGroupContact(type: String, id: String): MessageBuilder {
        return this
    }*/

    /*    fun addLocation(lat: String,lon: String,title: String,content: String): MessageBuilder {
            val data = Location(lat, lon, title, content)
            stringBuilder.run {
                append(ObjectMapper().writeValueAsString(data))
                append(",")
            }
            return this
        }*/
    fun addShareMusic(type: String, id: String): MessageBuilder {
        addObject(Music(type, id), CqcodeType.MUSIC)
        return this
    }

    fun addCustomShareMusic(
        type: String,
        url: String,
        audio: String,
        title: String,
        content: String,
        image: String
    ): MessageBuilder {
        addObject(CustomMusic(type, url, audio, title, content, image), CqcodeType.MUSIC)
        return this
    }

    fun addReply(
        id: Int
    ): MessageBuilder {
        addObject(Reply(id), CqcodeType.REPLY)
        return this
    }

    fun addCustomReply(
        id: Int,
        text: String,
        qq: Long,
        time: Long,
        seq: Long
    ): MessageBuilder {
        addObject(CustomReply(id, text, qq, time, seq), CqcodeType.REPLY)
        return this
    }

    fun addForward(id: String): MessageBuilder {
        addObject(Forward(id), CqcodeType.FORWARD)
        return this
    }

    /*fun addNode(id: String): MessageBuilder {
        return this
    }
    fun addNodes(user_id: String,nickname: String,content: String): MessageBuilder {
        return this
    }*/
    fun addXMLMsg(data: String): MessageBuilder {
        addObject(XMLOrJsonMessage(data), CqcodeType.XML)
        return this
    }

    fun addJsonMsg(data: String): MessageBuilder {
        addObject(XMLOrJsonMessage(data), CqcodeType.JSON)
        return this
    }

    fun addCardImage(
        file: String,
        minWidth: Long = 400,
        minHeight: Long = 400,
        maxWidth: Long = 500,
        maxHeight: Long = 1000,
        source: String = "",
        icon: String = ""
    ): MessageBuilder {
        addObject(CardImage(file, minWidth, minHeight, maxWidth, maxHeight, source, icon), CqcodeType.CARDIMAGE)
        return this
    }

    fun addTTS(text: String): MessageBuilder {
        addObject(TTS(text), CqcodeType.TTS)
        return this
    }

    fun addGift(qq: Long, id: String): MessageBuilder {
        addObject(Gift(qq, id), CqcodeType.GIFT)
        return this
    }

    fun build(): String {
        stringBuilder.append("]")
        return stringBuilder.toString()
    }

    private fun addObject(data: Any, type: CqcodeType) {
        stringBuilder.run {
            val mapper = ObjectMapper().registerKotlinModule()
            append(mapper.writeValueAsString(MessageArray(type.code, mapper.writeValueAsString(data))))
            append(",")
        }
    }
}