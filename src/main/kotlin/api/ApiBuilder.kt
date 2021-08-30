package api

import listener.Api

class ApiBuilder {
    data class ApiTemplate(
        val action: String,
        val params: String,
        val echo: String
    )

    fun <T: Api> build(paramsData: T): String {
        return "TODO"
    }
}