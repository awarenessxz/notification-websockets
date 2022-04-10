package com.example.notification.websocket

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.converter.StringMessageConverter
import org.springframework.messaging.simp.stomp.StompSessionHandler
import org.springframework.web.socket.client.standard.StandardWebSocketClient
import org.springframework.web.socket.messaging.WebSocketStompClient

@Configuration
@EnableConfigurationProperties(WebsocketClientProperties::class)
class StompClientAutoConfiguration(
    private val websocketClientProperties: WebsocketClientProperties,
    private val websocketService: AbstractWebsocketService
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
        stompClient.connect(websocketClientProperties.brokerUrl, stompSessionHandler)
        return stompClient
    }
}
