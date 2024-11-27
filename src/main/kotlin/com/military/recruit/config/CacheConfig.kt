package com.military.recruit.config

import com.military.recruit.common.extension.log
import com.military.recruit.config.env.CacheEnv
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.caffeine.CaffeineCache
import org.springframework.cache.caffeine.CaffeineCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@EnableCaching
@Configuration
class CacheConfig {

    @Bean
    fun cacheManager(): CacheManager {
        val cacheManager = CaffeineCacheManager()
        CacheEnv.entries.forEach { cacheType ->
            val caffeineCache = CaffeineCache(cacheType.cacheName, cacheType.caffeineSpec().build())
            log.info("[INIT] cache name = ${caffeineCache.name} cache = ${caffeineCache.nativeCache}")
            cacheManager.registerCustomCache(caffeineCache.name, caffeineCache.nativeCache)
        }
        return cacheManager
    }
}