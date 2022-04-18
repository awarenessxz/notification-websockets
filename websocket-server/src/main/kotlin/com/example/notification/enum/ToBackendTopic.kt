package com.example.notification.enum

import com.example.notification.constant.TopicConstants

/*
 * Topics from Frontend (proxy via websocket server) to Backend
 */

enum class ToBackendTopic(val destination: String) {
    GREETINGS(TopicConstants.TOPIC_GREETINGS);

    companion object {
        fun getTopics(): List<String> {
            return values().map { it.name }
        }

        fun isValidTopic(topic: String): Boolean {
            return getTopics().contains(topic)
        }
    }
}
