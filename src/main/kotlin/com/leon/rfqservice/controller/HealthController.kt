package com.leon.rfqservice.controller

import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/health")
class HealthController 
{
    private val logger = LoggerFactory.getLogger(HealthController::class.java)

    @GetMapping
    fun health(): ResponseEntity<Map<String, Any>> 
    {
        logger.info("Health check requested")
        
        val healthStatus = mapOf(
            "status" to "UP",
            "timestamp" to LocalDateTime.now().toString(),
            "service" to "rfq-service",
            "version" to "0.1.0"
        )
        
        return ResponseEntity.ok(healthStatus)
    }
}
