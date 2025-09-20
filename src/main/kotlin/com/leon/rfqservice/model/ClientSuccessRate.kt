package com.leon.rfqservice.model

import com.fasterxml.jackson.annotation.JsonProperty

data class ClientSuccessRate(
    @field:JsonProperty("clientName")
    val clientName: String,
    
    @field:JsonProperty("successRate")
    val successRate: Double,
    
    @field:JsonProperty("totalRfqs")
    val totalRfqs: Long
)
