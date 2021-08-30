package utils

import data.event.EventEnum
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class StringKtTest {
    private val jsonStr = "{\"anonymous\":null,\"font\":0,\"group_id\":955263823,\"message\":\"好热\",\"message_id\":1209504578,\"message_seq\":1202584,\"message_type\":\"group\",\"post_type\":\"message\",\"raw_message\":\"好热\",\"self_id\":395837251,\"sender\":{\"age\":0,\"area\":\"\",\"card\":\"xyz.akko\",\"level\":\"\",\"nickname\":\"akko\",\"role\":\"member\",\"sex\":\"unknown\",\"title\":\"\",\"user_id\":1378774701},\"sub_type\":\"normal\",\"time\":1630130601,\"user_id\":1378774701}"

    @Test
    fun readPostTyeTest() {
        println(jsonStr.readPostType())
        assertEquals(jsonStr.readPostType(),EventEnum.MESSAGE_EVENT)
    }
}