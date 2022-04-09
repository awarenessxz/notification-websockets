package com.example.notification.service

import com.example.notification.model.PubSubEvent
import org.slf4j.LoggerFactory
import org.springframework.data.redis.connection.ReactiveSubscription
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class RedisService(
    private val redisTemplate: ReactiveRedisTemplate<String, PubSubEvent>,
    private val websocketService: WebsocketService
) {
    companion object {
        private const val channel = "PUB-SUB-CHANNEL"
        private val logger = LoggerFactory.getLogger(RedisService::class.java)
    }

    fun publish(event: PubSubEvent) {
        logger.debug("Publishing to Redis Topic '$channel'...")
        redisTemplate.convertAndSend(channel, event).subscribe()
    }

    @PostConstruct
    fun subscribe() {
        logger.info("Subscribing to Redis Topic '$channel'...")
        redisTemplate.listenTo(ChannelTopic.of(channel))
            .map(ReactiveSubscription.Message<String, PubSubEvent>::getMessage)
            .subscribe {
                logger.debug("Receiving Redis Subscription --> $it")
                websocketService.sendToClient(it.destination, it.message)
            }
    }
}