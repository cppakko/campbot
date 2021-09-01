package api

import com.fasterxml.jackson.annotation.JsonRawValue
import com.fasterxml.jackson.databind.ObjectMapper
import listener.Api
import utils.ApiEndPoint
import java.util.*

class ApiBuilder<T: Api>(private val paramsData: T) {
    private data class ApiTemplate(
        val action: String,
        @JsonRawValue val params: String,
        val echo: String
    )

    fun build(): Pair<String,String> {
        val mapper = ObjectMapper()
        val apiPath = (paramsData::class.annotations[0] as ApiEndPoint).path
        val paramsStr = mapper.writeValueAsString(paramsData)
        val uuid = UUID.randomUUID().toString()
        return Pair(mapper.writeValueAsString(ApiTemplate(apiPath,paramsStr,uuid)),uuid)
    }
}