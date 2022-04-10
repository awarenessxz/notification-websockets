package com.example.notification.websocket

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.messaging.simp.stomp.StompFrameHandler
import org.springframework.messaging.simp.stomp.StompSession
import org.springframework.stereotype.Service

@Service
class WebsocketService(
    @Value("\${websocket-client.inbound-topics}") private val inboundTopics: List<String>,
) {
    lateinit var stompSession: StompSession

    companion object {
        private val logger = LoggerFactory.getLogger(WebsocketService::class.java)
    }

    fun subscribeAllTopics(handler: StompFrameHandler) {
        logger.info("Subscribing to all inbound topics") // todo put in application.yml
        inboundTopics.forEach { topic ->
            logger.info("Subscribing to $topic...")
            stompSession.subscribe(topic, handler)
        }
    }

    fun publishMessage(destination: String, message: String) {
        logger.info("Publishing message: $message to destination: $destination")
        stompSession.send(destination, message)
    }

    fun receivedMessage(destination: String?, message: String) {
        if (inboundTopics.contains(destination)) {
            logger.info("Processing Greetings!")
        } else {
            logger.info("Ignoring Unknown Destination - $destination")
        }
    }
}
