package com.example.notification.service

import com.example.notification.enum.ToFrontendTopic
import com.example.notification.enum.ToBackendTopic
import com.example.notification.model.WebsocketMessage
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service

@Service
class WebsocketService(private val template: SimpMessagingTemplate) {
    fun sendToFrontendClient(topic: ToFrontendTopic, message: String) {
        val payload = WebsocketMessage(topic.destination, message)
        template.convertAndSend(payload.topic, payload.message)
    }

    fun sendToBackendClient(topic: ToBackendTopic, message: String) {
        val payload = WebsocketMessage(topic.destination, message)
        template.convertAndSend(payload.topic, payload.message)
    }
}