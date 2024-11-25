package com.military.recruit.config.env

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("openapi")
data class OpenAPIEnv(
    val key: String,
    val baseurl: String
)
