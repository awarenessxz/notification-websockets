package com.example.notification.model

data class ApiMessage (
        val topic: ApiTopic,
        val payload: Any
)