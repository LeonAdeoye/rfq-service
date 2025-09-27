package com.leon.rfqservice.service

import com.leon.rfqservice.model.ClientStats
import com.leon.rfqservice.model.RfqStats
import com.leon.rfqservice.model.DailyStats
import com.leon.rfqservice.model.ClientSuccessRate
import com.leon.rfqservice.model.InstrumentStats

interface RfqStatsService
{
    fun getStatsByClient(client: String): RfqStats
    fun getClientStatsByStatus(status: String): List<ClientStats>
    fun getInstrumentStatsByStatus(status: String): List<InstrumentStats>
    fun getDailyStats(days: Int): List<DailyStats>
    fun getClientSuccessRates(): List<ClientSuccessRate>
    fun getTodayStats(): RfqStats
}

