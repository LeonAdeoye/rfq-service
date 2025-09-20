package com.leon.rfqservice.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.leon.rfqservice.model.enums.RfqStatus

data class RfqStats(
    @field:JsonProperty("totalRfqs")
    val totalRfqs: Long,
    
    @field:JsonProperty("statusCounts")
    val statusCounts: Map<RfqStatus, Long>,
    
    @field:JsonProperty("clientStats")
    val clientStats: List<ClientStats>,
    
    @field:JsonProperty("dailyStats")
    val dailyStats: List<DailyStats>,
    
    @field:JsonProperty("tradedAwayCount")
    val tradedAwayCount: Long,
    
    @field:JsonProperty("tradeCompletedCount")
    val tradeCompletedCount: Long,
    
    @field:JsonProperty("averageResponseTime")
    val averageResponseTime: Double
)

data class ClientStats(
    @field:JsonProperty("clientName")
    val clientName: String,
    
    @field:JsonProperty("totalRfqs")
    val totalRfqs: Long,
    
    @field:JsonProperty("tradedAwayCount")
    val tradedAwayCount: Long,
    
    @field:JsonProperty("tradeCompletedCount")
    val tradeCompletedCount: Long,
    
    @field:JsonProperty("successRate")
    val successRate: Double
)

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
