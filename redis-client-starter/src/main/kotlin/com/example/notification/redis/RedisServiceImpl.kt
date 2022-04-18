package com.example.notification.redis

import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.stereotype.Service

@Service
class RedisServiceImpl(
    reactiveRedisTemplate: ReactiveRedisTemplate<String, String>,
    redisClientProperties: RedisClientProperties,
): AbstractRedisService(reactiveRedisTemplate, redisClientProperties) {
    override fun processReceivedMessage(topic: String, message: String) {
        logger.error("Please implement your own redisService to process the received message!")
    }
}
