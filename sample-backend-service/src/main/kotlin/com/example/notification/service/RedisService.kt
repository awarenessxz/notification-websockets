package com.example.notification.service

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.redis.connection.ReactiveSubscription
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class RedisService(
    private val reactiveRedisTemplate: ReactiveRedisTemplate<String, String>,
    @Value("\${redis-client.inbound-topics}") private val inboundTopics: List<String>,
) {
    companion object {
        private val logger = LoggerFactory.getLogger(RedisService::class.java)
    }

    fun publish(topic: String, message: String) {
        logger.debug("Publishing to Redis Channel: $topic")
        reactiveRedisTemplate.convertAndSend(topic, message).subscribe()
    }

    fun processReceivedMessage(topic: String, message: String) {
        logger.info("Processing Redis Event - topic: $topic, message: $message")
    }

    @PostConstruct
    fun subscribe() {
        inboundTopics.forEach { topic ->
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
