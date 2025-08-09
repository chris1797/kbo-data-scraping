package com.kboDataCenter.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/health")
class HealthController {
    
    @GetMapping
    fun health(): Map<String, String> = mapOf("status" to "UP")
}


