package com.example.notification.enum

/*
 * Topics from External Service to Backend to Frontend
 */

enum class ToClientTopic(val destination: String) {
    TOAST_MESSAGE("/topic/toast_message");

    companion object {
        fun getTopics(): List<String> {
            return values().map { it.name }
        }
    }
}