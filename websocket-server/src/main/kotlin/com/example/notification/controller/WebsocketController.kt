package com.example.notification.controller

import com.example.notification.enum.ToBackendTopic
import com.example.notification.enum.ToFrontendTopic
import com.example.notification.model.PubSubEvent
import com.example.notification.service.RedisService
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Controller

/**
 * Controller for handling websocket communication between Frontend and Backend
 **/

@Controller
class WebsocketController(private val redisService: RedisService) {
    @MessageMapping("/test")
    fun broadcastTestMessage(@Payload message: String) {
        redisService.publish(PubSubEvent(ToFrontendTopic.TOAST_MESSAGE.destination, message))
        redisService.publish(PubSubEvent(ToBackendTopic.GREETINGS.destination, message))
    }
}
