package com.example.notification.controller

import com.example.notification.enum.ToClientTopic
import com.example.notification.enum.ToServerTopic
import com.example.notification.model.ToClientMessage
import com.example.notification.model.TopicResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.web.bind.annotation.*

/**
 * Controller for handling external service notification to frontend using backend as a proxy
 **/

@RestController
@RequestMapping("/api/notification")
class NotificationController(private val template: SimpMessagingTemplate) {
    @GetMapping("/topics")
    fun getAllTopics(): ResponseEntity<TopicResponse> {
        val response = TopicResponse(toClientTopics = ToClientTopic.getTopics(), toServerTopics = ToServerTopic.getTopics())
        return ResponseEntity(response, HttpStatus.OK)
    }

    @PostMapping
    fun newTopicMessage(@RequestBody message: ToClientMessage) {
        template.convertAndSend(message.topic.destination, message.payload)
    }
}