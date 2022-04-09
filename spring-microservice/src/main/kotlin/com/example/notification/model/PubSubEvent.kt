package com.example.notification.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

data class PubSubEvent(
    @JsonProperty("origin") val origin: String,
    @JsonProperty("topic") val topic: String,
    @JsonProperty("message") val message: String
): Serializable
