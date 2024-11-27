package com.military.recruit.config.env

import com.github.benmanes.caffeine.cache.Caffeine
import java.util.concurrent.TimeUnit

/**
 * Cache environment
 * In-Memory Cache 설정 값
 *
 * @property cacheName 캐시 이름
 * @property expireAfterWrite 만료 시간
 * @property maximumSize 최대 크기
 * @author Juwon Lee
 */
enum class CacheEnv(
    val cacheName: String,
    private val expireAfterWrite: Long,
    private val maximumSize: Long) {
        ARMY("army", 30, 1000),
        NAVY("navy", 30, 10000),
        MARINE_CORPS("marineCorps", 30, 10000),
        AIR_FORCE("airForce", 30, 10000);

        fun caffeineSpec(): Caffeine<Any, Any> {
            return Caffeine.newBuilder()
                .maximumSize(maximumSize)
                .expireAfterWrite(expireAfterWrite, TimeUnit.MINUTES)
        }
}