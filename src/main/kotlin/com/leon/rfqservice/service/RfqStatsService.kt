package com.leon.rfqservice.service

import com.leon.rfqservice.model.RfqStats
import java.time.LocalDateTime

interface RfqStatsService 
{
    fun getRfqStats(): RfqStats
    
    fun getRfqStatsByDateRange(startDate: LocalDateTime, endDate: LocalDateTime): RfqStats
    
    fun getRfqStatsByClient(client: String): RfqStats
    
    fun getRfqStatsByStatus(status: String): RfqStats
    
    fun getDailyRfqStats(days: Int): List<DailyStats>
    
    fun getClientSuccessRates(): List<ClientSuccessRate>
}

data class DailyStats(
    val date: String,
    val totalRfqs: Long,
    val tradedAwayCount: Long,
    val tradeCompletedCount: Long
)

data class ClientSuccessRate(
    val clientName: String,
    val successRate: Double,
    val totalRfqs: Long
)
