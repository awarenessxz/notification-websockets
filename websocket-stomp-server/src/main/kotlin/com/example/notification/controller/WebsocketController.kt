package com.example.notification.controller

import org.slf4j.LoggerFactory
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Controller

/**
 * Controller for handling websocket communication between Frontend and Backend
 **/

@Controller
class WebsocketController() {
    companion object {
        private val logger = LoggerFactory.getLogger(WebsocketController::class.java)
    }

    @MessageMapping("/test")
    fun broadcastTestMessage(@Payload message: String) {
        logger.debug("Receiving from Websocket InboundClientChannel - topic: /test, message: $message")
    }
}
