package message

import com.fasterxml.jackson.databind.ObjectMapper
import data.Location

//https://github.com/botuniverse/onebot/blob/master/v11/specs/message/segment.md
class MessageBuilder {
    data class MessageArray(
        val type: String,
        val data: String
    )
    private val stringBuilder = StringBuilder()
    fun addText(text: String): MessageBuilder {
        return this
    }
    fun addFace(faceId: Int): MessageBuilder {
        return this
    }
    fun addImage(file: String,type: String,cache: String = "1",proxy: String = "1",timeout: String = "-1"): MessageBuilder {
        return this
    }
    fun addImage(file: String,cache: String = "1",proxy: String = "1",timeout: String = "-1"): MessageBuilder {
        return this
    }
    fun addRecord(file: String,magic: String = "0",cache: String = "1",proxy: String = "1",timeout: String): MessageBuilder {
        return this
    }
    fun addVideo(file: String,cache: String = "1",proxy: String = "1",timeout: String): MessageBuilder {
        return this
    }
    fun addAt(user_id: String): MessageBuilder {
        return this
    }
    fun addRPS(): MessageBuilder {
        return this
    }
    fun addDice(): MessageBuilder {
        return this
    }
    fun addShake(): MessageBuilder {
        return this
    }
    fun addPoke(type: String,id: String): MessageBuilder {
        return this
    }
    fun addAnonymous(ignore: String = "0"): MessageBuilder {
        return this
    }
    fun addShare(url: String,title: String,content: String,image: String): MessageBuilder {
        return this
    }
    fun addFriendContact(type: String,id: String): MessageBuilder {
        return this
    }
    fun addGroupContact(type: String,id: String): MessageBuilder {
        return this
    }
    fun addLocation(lat: String,lon: String,title: String,content: String): MessageBuilder {
        val data = Location(lat, lon, title, content)
        stringBuilder.run {
            append(ObjectMapper().writeValueAsString(data))
            append(",")
        }
        return this
    }
    fun addShareMusic(type: String,id: String): MessageBuilder {
        return this
    }
    fun addCustomShareMusic(type: String,url: String,audio: String,title: String,content: String,image: String): MessageBuilder {
        return this
    }
    fun addReply(id: String): MessageBuilder {
        return this
    }
    fun addForward(id: String): MessageBuilder {
        return this
    }
    fun addNode(id: String): MessageBuilder {
        return this
    }
    fun addNodes(user_id: String,nickname: String,content: String): MessageBuilder {
        return this
    }
    fun addXMLOrJsonMsg(data: String): MessageBuilder {
        return this
    }
    fun build(): String {
        stringBuilder.append("]")
        return stringBuilder.toString()
    }
}