package com.example.notification.enum

/*
 * Topics from Frontend to Backend
 */

enum class ToServerTopic(val label: String, val endpoint: String? = "") {
    NA("not applicable");

    companion object {
        fun getTopics(): List<String> {
            return values().map { it.name }
        }
    }
}
