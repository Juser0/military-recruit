package com.military.recruit.config.env

import com.github.benmanes.caffeine.cache.Caffeine
import java.util.concurrent.TimeUnit

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