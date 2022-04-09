package com.example.notification.service

import org.slf4j.LoggerFactory
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service

@Service
class WebsocketService(private val template: SimpMessagingTemplate) {
    companion object {
        private val logger = LoggerFactory.getLogger(WebsocketService::class.java)
    }

    fun sendToClient(destination: String, message: String) {
        logger.debug("Sending to Websocket OutboundClientChannel: $destination - $message")
        template.convertAndSend(destination, message)
    }
}