package com.example.notification.config

import com.example.notification.enum.ToClientTopic
import com.example.notification.model.ToClientMessage
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled

/**
 * Mocking Message
 **/

@Configuration
@EnableScheduling
class SchedulerConfig(private val template: SimpMessagingTemplate) {
    @Scheduled(fixedRate = 60000) // every 1 minute
    fun sendMessage() {
        val message = ToClientMessage(topic = ToClientTopic.TOAST_MESSAGE, "Mock Message")
        template.convertAndSend(message.topic.destination, message.payload)
    }
}
