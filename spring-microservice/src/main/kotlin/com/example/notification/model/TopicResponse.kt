package com.example.notification.model

data class TopicResponse(
    val toClientTopics: List<String>,
    val toServerTopics: List<String>
)
