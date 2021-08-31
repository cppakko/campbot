package api

import listener.Api
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import utils.ApiEndPoint

internal class ApiBuilderTest {
    @ApiEndPoint("send_private_msg")
    data class TestApiData(
        val user_id: Long,
        val message: String
    ): Api
    @Test
    fun test() {
        val str = "{ \"action\": \"send_private_msg\", \"params\": { \"user_id\": 10001000, \"message\": \"你好\" }, \"echo\": \"123\" }"
        val api = ApiBuilder<TestApiData>(TestApiData(10001000,"你好")).build()
        assertEquals(str,api.first)
    }
}