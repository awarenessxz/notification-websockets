package com.example.notification.enum

/*
 * Topics from Frontend to Backend
 */

enum class ToBackendTopic(val destination: String) {
    NA("not applicable");

    companion object {
        fun getTopics(): List<String> {
            return values().map { it.name }
        }

        fun isValidTopic(topic: String): Boolean {
            return getTopics().contains(topic)
        }
    }
}
