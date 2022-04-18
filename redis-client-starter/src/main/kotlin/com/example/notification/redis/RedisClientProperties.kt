package com.example.notification.redis

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "redis-client")
data class RedisClientProperties(
    val inboundTopics: List<String>? = listOf(),
    val outboundTopics: List<String>? = listOf()
)
