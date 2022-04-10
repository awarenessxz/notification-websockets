package com.example.notification.websocket

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "websocket-client")
data class WebsocketClientProperties(
    val brokerUrl: String,
    val inboundTopics: List<String>
)
