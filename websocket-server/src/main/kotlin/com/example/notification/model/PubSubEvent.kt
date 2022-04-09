package com.example.notification.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

data class PubSubEvent(
    @JsonProperty("destination") val destination: String,
    @JsonProperty("message") val message: String
): Serializable
