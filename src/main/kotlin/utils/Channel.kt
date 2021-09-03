package utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import data.api.ApiResponse
import exceptions.ApiCallFailedException
import kotlinx.coroutines.channels.Channel
import network.websocket.WebSocketHelper

@Throws(ApiCallFailedException::class)
suspend inline fun <reified T> Channel<String>.getReturnValue(apiMsg: String): ApiResponse<T> {
    WebSocketHelper.callApiChannel.send(Pair(apiMsg, this))
    for (str in this) {
        //TODO OPTI
        val mapper = ObjectMapper().registerKotlinModule()
        val jsonNodeTree = mapper.readTree(str)
        if (jsonNodeTree.get("status").toString() == "\"ok\"") {
            val obj = mapper.readValue<ApiResponse<T>>(str)
            val data = jsonNodeTree.get("data").toString().asJsonObject<T>()
            return ApiResponse(data, obj.echo, obj.retcode, obj.status)
        } else throw ApiCallFailedException(jsonNodeTree.get("msg").toString(), jsonNodeTree.get("wording").toString())
    }
    throw ApiCallFailedException("NULL_KNOW", "未知错误")
}