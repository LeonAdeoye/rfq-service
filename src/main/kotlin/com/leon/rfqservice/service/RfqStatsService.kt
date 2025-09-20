package com.leon.rfqservice.service

import com.leon.rfqservice.model.RfqStats
import com.leon.rfqservice.model.DailyStats
import com.leon.rfqservice.model.ClientSuccessRate

interface RfqStatsService
{
    fun getRfqStatsByClient(client: String): RfqStats
    
    fun getRfqStatsByStatus(status: String): RfqStats
    
    fun getDailyRfqStats(days: Int): List<DailyStats>
    
    fun getClientSuccessRates(): List<ClientSuccessRate>
}

