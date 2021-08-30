package utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

class JacksonObjectMapper {
    companion object {
        val mapper = ObjectMapper().registerKotlinModule()
    }
}