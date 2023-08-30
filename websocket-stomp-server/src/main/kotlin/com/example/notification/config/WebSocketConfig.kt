package com.example.notification.config

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer

@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig: WebSocketMessageBrokerConfigurer {
    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint("/stomp").setAllowedOrigins("*")   // websocket endpoint
    }

    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        //registry.enableSimpleBroker("/topic")                         // server -> client (subscriber)
        registry.enableStompBrokerRelay("/topic")
            .setRelayHost("localhost")
            .setRelayPort(61613)
            .setClientLogin("guest")
            .setClientPasscode("guest")
            .setSystemHeartbeatReceiveInterval(30000)
            .setSystemHeartbeatSendInterval(30000)
        registry.setApplicationDestinationPrefixes("/server")          // client -> server
    }
}
