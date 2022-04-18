package com.example.notification.enum

import com.example.notification.constant.TopicConstants

/*
 * Topics from Backend (proxy via Websocket Server) to Frontend
 */

enum class ToFrontendTopic(val destination: String) {
    TOAST_MESSAGE(TopicConstants.TOPIC_TOAST_MESSAGE);

    companion object {
        fun getTopics(): List<String> {
            return values().map { it.name }
        }

        fun isValidTopic(topic: String): Boolean {
            return getTopics().contains(topic)
        }
    }
}
