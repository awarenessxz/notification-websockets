package com.example.notification.controller

import com.example.notification.enum.ToFrontendTopic
import com.example.notification.enum.ToBackendTopic
import com.example.notification.model.BroadcastEvent
import com.example.notification.model.NewMessageRequest
import com.example.notification.model.TopicResponse
import com.example.notification.service.RedisService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * Controller for handling external backend service's notification to frontend using websocket server as a proxy
 **/

@RestController
@RequestMapping("/api/notification")
class NotificationController(private val redisService: RedisService) {
    @GetMapping("/topics")
    fun getAllTopics(): ResponseEntity<TopicResponse> {
        val response = TopicResponse(ToFrontendTopic.getTopics(), ToBackendTopic.getTopics())
        return ResponseEntity(response, HttpStatus.OK)
    }

    @PostMapping
    fun newMessage(@RequestBody message: NewMessageRequest) {
        if (ToFrontendTopic.isValidTopic(message.topic)) {
            val topic = ToFrontendTopic.valueOf(message.topic)
            redisService.broadcastInternally(BroadcastEvent(topic.destination, message.message))
        }
        throw IllegalArgumentException("Invalid Topic!! - ${message.topic}")
    }
}
