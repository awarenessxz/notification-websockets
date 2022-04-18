package com.example.notification.service

import com.example.notification.enum.ToBackendTopic
import com.example.notification.enum.ToFrontendTopic
import com.example.notification.model.BroadcastEvent
import org.slf4j.LoggerFactory
import org.springframework.data.redis.connection.ReactiveSubscription
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class RedisService(
    private val broadcastRedisTemplate: ReactiveRedisTemplate<String, BroadcastEvent>,
    private val reactiveRedisTemplate: ReactiveRedisTemplate<String, String>,
    private val websocketService: WebsocketService
) {
    companion object {
        private const val broadcastChannel = "INTERNAL-BROADCAST-CHANNEL"
        private val logger = LoggerFactory.getLogger(RedisService::class.java)
    }

    fun broadcastInternally(event: BroadcastEvent) {
        logger.debug("Broadcasting $event to other websocket servers...")
        broadcastRedisTemplate.convertAndSend(broadcastChannel, event).subscribe()
    }

    @PostConstruct
    fun broadcastListener() {
        logger.info("Subscribing to Redis Channel: $broadcastChannel")
        broadcastRedisTemplate.listenTo(ChannelTopic.of(broadcastChannel))
            .map(ReactiveSubscription.Message<String, BroadcastEvent>::getMessage)
            .subscribe { event ->
                logger.debug("Receiving Redis Broadcast Event --> $event")
                websocketService.sendToClient(event.destination, event.message)
            }
    }

    fun publish(topic: String, message: String) {
        logger.debug("Publishing to Redis Channel: $topic")
        reactiveRedisTemplate.convertAndSend(topic, message).subscribe()
    }

    fun subscribe(topic: String, destination: String) {
        logger.info("Subscribing to Redis Channel: $topic")
        reactiveRedisTemplate.listenTo(ChannelTopic.of(topic))
            .map(ReactiveSubscription.Message<String, String>::getMessage)
            .subscribe { message ->
                logger.debug("Receiving Redis Event --> $message")
                websocketService.sendToClient(destination, message)
            }
    }

    @PostConstruct
    fun subscribe() {
        ToBackendTopic.values().forEach { topic ->
            subscribe(topic.name, topic.destination)
        }
        ToFrontendTopic.values().forEach { topic ->
            subscribe(topic.name, topic.destination)
        }
    }
}
