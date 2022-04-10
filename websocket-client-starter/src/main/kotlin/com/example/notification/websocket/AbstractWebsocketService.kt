package com.example.notification.websocket

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.messaging.simp.stomp.StompFrameHandler
import org.springframework.messaging.simp.stomp.StompSession

abstract class AbstractWebsocketService(
    private val websocketClientProperties: WebsocketClientProperties
) {
    companion object {
        val logger: Logger = LoggerFactory.getLogger(AbstractWebsocketService::class.java)
    }

    var stompSession: StompSession? = null

    abstract fun processReceivedMessage(destination: String, message: String)

    private fun subscribeAllTopics(handler: StompFrameHandler) {
        logger.info("Subscribing to all inbound topics")
        websocketClientProperties.inboundTopics.forEach { topic ->
            logger.info("Subscribing to $topic...")
            stompSession?.subscribe(topic, handler)
        }
    }

    fun initWebsocketSession(stompSession: StompSession, handler: StompFrameHandler) {
        this.stompSession = stompSession
        subscribeAllTopics(handler)
    }

    fun publishMessage(destination: String, message: String) {
        if (stompSession != null) {
            logger.info("Publishing message: $message to destination: $destination")
            stompSession!!.send(destination, message)
        } else {
            logger.warn("Publishing Failed! No Websocket Connection Established...")
        }
    }

    fun receivedMessage(destination: String?, message: String) {
        if (websocketClientProperties.inboundTopics.contains(destination)) {
            logger.info("Received message: $message from destination: $destination")
            processReceivedMessage(destination!!, message)
        } else {
            logger.info("Ignoring Unknown Destination - $destination")
        }
    }
}
