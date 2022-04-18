package com.example.notification.service

import com.example.notification.websocket.AbstractWebsocketService
import com.example.notification.websocket.WebsocketClientProperties
import org.springframework.stereotype.Service

@Service
class WebsocketService(websocketClientProperties: WebsocketClientProperties): AbstractWebsocketService(websocketClientProperties) {
    override fun processReceivedMessage(destination: String, message: String) {
        logger.info("Processing websocket message - destination: $destination, message: $message")
    }
}