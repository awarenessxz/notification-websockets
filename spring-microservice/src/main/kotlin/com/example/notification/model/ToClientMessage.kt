package com.example.notification.model

import com.example.notification.enum.ToClientTopic

data class ToClientMessage (
        val topic: ToClientTopic,
        val payload: Any
)