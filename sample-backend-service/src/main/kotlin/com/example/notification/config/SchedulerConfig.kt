package com.example.notification.config

import com.example.notification.websocket.WebsocketServiceImpl
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled

/**
 * Mocking Message (Testing)
 **/

@Configuration
@EnableScheduling
class SchedulerConfig(private val websocketService: WebsocketServiceImpl) {
    @Scheduled(fixedRate = 60000) // every 1 minute
    fun sendToServer() {
        websocketService.publishMessage("/server/test", "From Backend Service (Hello)")
    }
}