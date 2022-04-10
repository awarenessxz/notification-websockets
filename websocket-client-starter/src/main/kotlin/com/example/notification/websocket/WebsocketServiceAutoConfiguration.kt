package com.example.notification.websocket

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@ConditionalOnClass(AbstractWebsocketService::class)
@EnableConfigurationProperties(WebsocketClientProperties::class)
class WebsocketServiceAutoConfiguration(private val websocketClientProperties: WebsocketClientProperties) {
    @Bean
    @ConditionalOnMissingBean
    fun websocketService(): AbstractWebsocketService {
        return WebsocketServiceImpl(websocketClientProperties)
    }
}