package com.example.notification.config

import com.example.notification.enum.ToFrontendTopic
import com.example.notification.enum.ToBackendTopic
import com.example.notification.model.PubSubEvent
import com.example.notification.service.RedisService
import com.example.notification.service.WebsocketService
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled

/**
 * Mocking Message (Testing)
 **/

@Configuration
@EnableScheduling
class SchedulerConfig(private val websocketService: WebsocketService, private val redisService: RedisService) {
    @Scheduled(fixedRate = 60000) // every 1 minute
    fun sendMessage() {
        websocketService.sendToClient(ToFrontendTopic.TOAST_MESSAGE.destination, "Mock Message")
        websocketService.sendToClient(ToBackendTopic.NA.destination, "Mock Message")
    }

    @Scheduled(fixedRate = 60000) // every 1 minute
    fun sendToServer() {
        redisService.publish(PubSubEvent(ToFrontendTopic.TOAST_MESSAGE.name, "Testing Message"))
        //redisService.publish(PubSubEvent(ToServerTopic.NA.name, "Testing Message"))
    }
}
