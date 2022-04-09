package com.example.notification.controller

import com.example.notification.service.RedisService
import org.springframework.stereotype.Controller

/**
 * Controller for handling websocket communication between Frontend and Backend
 **/

@Controller
class WebsocketController(private val redisService: RedisService) {

}