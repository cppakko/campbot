package data

data class Text(
    val text: String
)

data class Face(
    val id: Int
)

data class Image(
    val file: String,
    val type: String,
    val cache: Int,
    val proxy: Int,
    val timeout:Long
)

data class ImageWithoutType(
    val file: String,
    val cache: Int,
    val proxy: Int,
    val timeout:Long
)

data class Record(
    val file: String,
    val magic: Int,
    val cache: Int,
    val proxy: Int,
    val timeout:Long
)

data class Video(
    val file: String,
    val cache: Int,
    val proxy: Int,
    val timeout:Long
)

data class At(
    val qq: String
)

data class Poke(
    val type: String,
    val id: String,
)

data class Share(
    val url: String,
    val title: String,
    val content: String,
    val image: String
)

data class Contact(
    val type: String,
    val id: String
)

data class Location(
    val lat: String,
    val lon: String,
    val title: String,
    val content: String
)

data class Music(
    val type: String,
    val id: String
)

data class CustomMusic(
    val type: String,
    val url: String,
    val audio: String,
    val title: String,
    val content: String,
    val image: String
)

data class Reply(
    val id: String
)

data class Forward(
    val id: String
)
data class Node(
    val id: String
)

data class CustomNode(
    val user_id: String,
    val nickname: String,
    val content: String
)

data class XMLOrJsonMessage(
    val data: String
)