package com.example.notification.websocket

import org.springframework.stereotype.Service

@Service
class WebsocketServiceImpl(websocketClientProperties: WebsocketClientProperties): AbstractWebsocketService(websocketClientProperties) {
    override fun processReceivedMessage(destination: String, message: String) {
        logger.error("Please implement your own websocketService to process the received message!")
    }
}
