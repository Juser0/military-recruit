package com.military.recruit.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.servers.Server
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Swagger Configuration
 * Swagger UI를 위한 설정 파일
 *
 * @author Juwon Lee
 */
@Configuration
@OpenAPIDefinition(servers = [
    Server(url = "/", description = "Default Server URL")
])
class SwaggerConfig {

    @Bean
    fun openAPI() : OpenAPI {
        return OpenAPI()
            .info(configurationInfo())
    }

    /** OAS 정보 */
    private fun configurationInfo(): Info {
        return Info()
            .title("Military Recruit Viewer")
            .description("병무청 실시간 모집 현황 뷰어 Server")
            .version("1.0.0")
    }
}