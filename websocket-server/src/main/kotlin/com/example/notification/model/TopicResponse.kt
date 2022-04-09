package com.example.notification.model

data class TopicResponse(
    val toFrontendTopics: List<String>,
    val toBackendTopics: List<String>
)
