package com.example.notification.model

data class NewMessageRequest(
    val topic: String,
    val message: String
)