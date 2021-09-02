@file:Suppress("unused")
package message

import com.fasterxml.jackson.annotation.JsonRawValue
import com.fasterxml.jackson.databind.ObjectMapper
import data.CardImage
import data.TTS

//https://github.com/botuniverse/onebot/blob/master/v11/specs/message/segment.md
class MessageBuilder {
    data class MessageArray(
        val type: String,
        @JsonRawValue val data: String
    )

    private val stringBuilder = StringBuilder()

    init {
        stringBuilder.append("[")
    }

    fun addText(text: String): MessageBuilder {
        return this
    }

    fun addFace(faceId: Int): MessageBuilder {
        return this
    }

    fun addImage(file: String, type: String, cache: String = "1", id: String = "40000", c: Int = 3): MessageBuilder {
        return this
    }

    fun addImage(file: String, cache: String = "1", id: String = "40000", c: Int = 3): MessageBuilder {
        return this
    }

    fun addRecord(
        file: String,
        magic: String = "0",
        cache: String = "1",
        proxy: String = "1",
        timeout: String
    ): MessageBuilder {
        return this
    }

    fun addVideo(file: String, cover: String, c: Int = 3): MessageBuilder {
        return this
    }

    fun addAt(user_id: String, name: String): MessageBuilder {
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
        return this
    }

    fun addAnonymous(ignore: String = "0"): MessageBuilder {
        return this
    }

    fun addShare(url: String, title: String, content: String, image: String): MessageBuilder {
        return this
    }

    fun addFriendContact(type: String, id: String): MessageBuilder {
        return this
    }

    fun addGroupContact(type: String, id: String): MessageBuilder {
        return this
    }

    /*    fun addLocation(lat: String,lon: String,title: String,content: String): MessageBuilder {
            val data = Location(lat, lon, title, content)
            stringBuilder.run {
                append(ObjectMapper().writeValueAsString(data))
                append(",")
            }
            return this
        }*/
    fun addShareMusic(type: String, id: String): MessageBuilder {
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
        return this
    }

    fun addReply(
        id: Int,
        text: String,
        qq: Long,
        time: Long,
        seq: Long
    ): MessageBuilder {
        return this
    }

    fun addForward(id: String): MessageBuilder {
        return this
    }

    //TODO
    /*fun addNode(id: String): MessageBuilder {
        return this
    }
    fun addNodes(user_id: String,nickname: String,content: String): MessageBuilder {
        return this
    }*/
    fun addXMLOrJsonMsg(data: String): MessageBuilder {
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
        addObject(MessageArray("cardimage",
            ObjectMapper().writeValueAsString(CardImage(
                file,minWidth,minHeight,maxWidth,maxHeight,source,icon
            ))))
        return this
    }

    fun addTTS(text: String): MessageBuilder {
        addObject(TTS(text))
        return this
    }

    fun build(): String {
        stringBuilder.append("]")
        return stringBuilder.toString()
    }

    private fun addObject(data: Any) {
        stringBuilder.run {
            append(ObjectMapper().writeValueAsString(data))
            append(",")
        }
    }
}