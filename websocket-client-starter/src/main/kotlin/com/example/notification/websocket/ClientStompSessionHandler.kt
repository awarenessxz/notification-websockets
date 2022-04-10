package com.example.notification.websocket

import org.slf4j.LoggerFactory
import org.springframework.messaging.simp.stomp.*

class ClientStompSessionHandler(private val websocketService: AbstractWebsocketService): StompSessionHandlerAdapter() {
    companion object {
        private val logger = LoggerFactory.getLogger(ClientStompSessionHandler::class.java)
    }

    override fun afterConnected(session: StompSession, connectedHeaders: StompHeaders) {
        logger.info("New session established : " + session.sessionId)
        websocketService.initWebsocketSession(session, this)
    }

    override fun handleException(session: StompSession, command: StompCommand?, headers: StompHeaders, payload: ByteArray, exception: Throwable) {
        logger.error("Websocket Error: ${exception.message}")
    }

    override fun handleTransportError(session: StompSession, exception: Throwable) {
        logger.error("Websocket Transport Error: ${exception.message}")
        // todo: schedule a reconnection attempt
    }

    override fun handleFrame(headers: StompHeaders, payload: Any?) {
        logger.info("Received: payload: $payload, headers: $headers")
        websocketService.receivedMessage(headers.destination, payload as String)
    }
}
