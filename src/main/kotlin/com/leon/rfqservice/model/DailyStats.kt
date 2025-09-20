package com.leon.rfqservice.model

import com.fasterxml.jackson.annotation.JsonProperty

data class DailyStats(
    @field:JsonProperty("date")
    val date: String,
    
    @field:JsonProperty("totalRfqs")
    val totalRfqs: Long,
    
    @field:JsonProperty("tradedAwayCount")
    val tradedAwayCount: Long,
    
    @field:JsonProperty("tradeCompletedCount")
    val tradeCompletedCount: Long
)
