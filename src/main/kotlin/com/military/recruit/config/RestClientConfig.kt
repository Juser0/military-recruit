package com.military.recruit.config

import com.military.recruit.remote.receipt.ReceiptClient
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
class RestClientConfig {

    @Bean
    fun militaryClient(): ReceiptClient {
        val restClient: RestClient = RestClient.builder()
            .baseUrl("https://www.naver.com")
            .defaultHeader("X-AUTH-KEY", "default")
            .build()
        val adapter: RestClientAdapter = RestClientAdapter.create(restClient)
        val factory: HttpServiceProxyFactory = HttpServiceProxyFactory.builderFor(adapter).build()
        return factory.createClient(ReceiptClient::class.java)
    }
}