package com.example.notification.service

import com.example.notification.redis.AbstractRedisService
import com.example.notification.redis.RedisClientProperties
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.stereotype.Service

@Service
class RedisService(
    reactiveRedisTemplate: ReactiveRedisTemplate<String, String>,
    redisClientProperties: RedisClientProperties
): AbstractRedisService(reactiveRedisTemplate, redisClientProperties) {
    override fun processReceivedMessage(topic: String, message: String) {
        logger.info("Processing redis event - topic: $topic, message: $message")
    }
}