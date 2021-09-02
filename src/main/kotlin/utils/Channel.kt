package utils

import data.api.ApiResponse
import exceptions.ApiCallFailedException
import kotlinx.coroutines.channels.Channel
import network.websocket.WebSocketHelper

suspend inline fun <reified T> Channel<String>.getReturnValue(apiMsg: String): ApiResponse<T> {
    WebSocketHelper.callApiChannel.send(Pair(apiMsg, this))
    for (str in this) {
        this.close()
        return str.asJsonObject()
    }
    throw ApiCallFailedException()
}