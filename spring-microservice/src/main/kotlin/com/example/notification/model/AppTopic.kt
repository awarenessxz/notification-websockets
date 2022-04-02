package com.example.notification.model

/*
 * Topics from Frontend to Backend
 */

enum class AppTopic(val label: String, val endpoint: String? = "") {
    NA("not applicable");

    companion object {
        fun getTopics(): List<String> {
            return ApiTopic.values().map { it.name }
        }
    }
}