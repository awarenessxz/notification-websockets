package com.example.notification.redis

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.core.ReactiveRedisTemplate

@Configuration
@ConditionalOnClass(AbstractRedisService::class)
@EnableConfigurationProperties(RedisClientProperties::class)
class RedisServiceAutoConfiguration(
    private val reactiveRedisTemplate: ReactiveRedisTemplate<String, String>,
    private val redisClientProperties: RedisClientProperties,
) {
    @Bean
    @ConditionalOnMissingBean
    fun websocketService(): AbstractRedisService {
        return RedisServiceImpl(reactiveRedisTemplate, redisClientProperties)
    }
}
