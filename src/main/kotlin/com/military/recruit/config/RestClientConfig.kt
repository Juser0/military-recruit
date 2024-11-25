package com.military.recruit.config

import com.military.recruit.config.env.OpenAPIEnv
import com.military.recruit.remote.ReceiptClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient
import org.springframework.web.client.support.RestClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory

/**
 * RestClient Configuration
 * 외부 API 연동을 위한 설정 파일
 *
 * @author Juwon Lee
 */
@Configuration
class RestClientConfig(private val openAPIEnv: OpenAPIEnv) {

    @Bean
    fun militaryClient(): ReceiptClient {
        val restClient: RestClient = RestClient.builder()
            .baseUrl(openAPIEnv.baseurl)
            .build()
        val adapter: RestClientAdapter = RestClientAdapter.create(restClient)
        val factory: HttpServiceProxyFactory = HttpServiceProxyFactory.builderFor(adapter).build()
        return factory.createClient(ReceiptClient::class.java)
    }
}