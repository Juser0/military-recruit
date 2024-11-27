package com.military.recruit.config.env

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * OpenAPI environment
 * 공공 api 환경변수 가져오기
 *
 * @property key apiKey
 * @property baseurl
 * @author Juwon Lee
 */
@ConfigurationProperties("openapi")
data class OpenAPIEnv(
    val key: String,
    val baseurl: String
)
