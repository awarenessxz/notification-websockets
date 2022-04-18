package com.example.notification.config

import com.example.notification.service.RedisService
import com.example.notification.service.WebsocketService
import com.example.notification.websocket.WebsocketClientProperties
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled

/**
 * Mocking Message (Testing)
 **/

@Configuration
@EnableScheduling
class SchedulerConfig(
        private val websocketClientProperties: WebsocketClientProperties,
        private val websocketService: WebsocketService,
        private val redisService: RedisService,
        @Value("\${redis-client.outbound-topics}") private val outboundTopics: List<String>,
) {
    @Scheduled(fixedRate = 60000) // every 1 minute
    fun sendToServer() {
        websocketClientProperties.outboundTopics?.forEach { topic ->
            websocketService.publishMessage(topic, "From Backend Service (Hello)")
        }
        outboundTopics.forEach { topic ->
            redisService.publish(topic, "From Backend Service (Hello)")
        }
    }
}
