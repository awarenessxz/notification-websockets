package com.example.notification.websocket

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.converter.MappingJackson2MessageConverter
import org.springframework.messaging.converter.StringMessageConverter
import org.springframework.messaging.simp.stomp.StompSessionHandler
import org.springframework.web.socket.client.standard.StandardWebSocketClient
import org.springframework.web.socket.messaging.WebSocketStompClient

@Configuration
class StompClientConfig(
    @Value("\${websocket-client.broker-url}") private val websocketBrokerUrl: String,
    private val websocketService: WebsocketService
) {
    @Bean
    fun stompSessionHandler(): StompSessionHandler {
        return ClientStompSessionHandler(websocketService)
    }

    @Bean
    fun webSocketStompClient(stompSessionHandler: StompSessionHandler): WebSocketStompClient {
        val webSocketClient = StandardWebSocketClient()
        val stompClient = WebSocketStompClient(webSocketClient)
        stompClient.messageConverter = StringMessageConverter() // MappingJackson2MessageConverter()
        //stompClient.taskScheduler = ConcurrentTaskScheduler()
        stompClient.connect(websocketBrokerUrl, stompSessionHandler)
        return stompClient
    }
}
