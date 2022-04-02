package com.example.notification.model

/*
 * Topics from External Service to Backend to Frontend
 */

enum class ApiTopic(val label: String) {
    TOAST_MESSAGE("toast");

    companion object {
        fun getTopics(): List<String> {
            return values().map { it.name }
        }
    }
}