package com.example.notification.redis

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.redis.connection.ReactiveSubscription
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import javax.annotation.PostConstruct

abstract class AbstractRedisService(
    private val reactiveRedisTemplate: ReactiveRedisTemplate<String, String>,
    private val redisClientProperties: RedisClientProperties
) {
    companion object {
        val logger: Logger = LoggerFactory.getLogger(AbstractRedisService::class.java)
    }

    abstract fun processReceivedMessage(topic: String, message: String)

    fun publish(topic: String, message: String) {
        logger.debug("Publishing to Redis Channel: $topic")
        reactiveRedisTemplate.convertAndSend(topic, message).subscribe()
    }

    @PostConstruct
    fun subscribe() {
        redisClientProperties.inboundTopics?.forEach { topic ->
            logger.info("Subscribing to Redis Channel: $topic")
            reactiveRedisTemplate.listenTo(ChannelTopic.of(topic))
                .map(ReactiveSubscription.Message<String, String>::getMessage)
                .subscribe { message ->
                    logger.debug("Receiving Redis Event --> $message")
                    processReceivedMessage(topic, message)
                }
        }
    }
}
