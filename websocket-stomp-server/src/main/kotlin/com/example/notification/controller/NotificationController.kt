package com.example.notification.controller

import com.example.notification.constant.TopicConstants
import com.example.notification.service.WebsocketService
import org.springframework.web.bind.annotation.*

/**
 * Controller for handling external backend service's notification to frontend using websocket server as a proxy
 **/

@RestController
@RequestMapping("/api/notification")
class NotificationController(private val websocketService: WebsocketService) {
    @PostMapping
    fun newMessage(@RequestBody message: String) {
        websocketService.sendToClient(TopicConstants.TOPIC_TOAST_MESSAGE, message)
    }
}
