package com.leon.rfqservice.service

import com.leon.rfqservice.model.ClientStats
import com.leon.rfqservice.model.RfqStats
import com.leon.rfqservice.model.DailyStats
import com.leon.rfqservice.model.ClientSuccessRate
import com.leon.rfqservice.model.InstrumentStats
import com.leon.rfqservice.model.enums.RfqStatus
import com.leon.rfqservice.repository.RfqRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Service
class RfqStatsServiceImpl @Autowired constructor(private val rfqRepository: RfqRepository) : RfqStatsService
{
    override fun getStatsByClient(client: String): RfqStats
    {
        val today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val clientRfqs = rfqRepository.findByActiveTrueAndClientAndTradeDate(client, today)
        val totalRfqs = clientRfqs.size.toLong()
        val statusCounts = RfqStatus.values().associateWith { status ->
            clientRfqs.count { it.status == status }.toLong()
        }
        
        return RfqStats(
            totalRfqs = totalRfqs,
            statusCounts = statusCounts,
        )
    }

    override fun getClientStatsByStatus(status: String): List<ClientStats>
    {
        val statusEnum = RfqStatus.valueOf(status)
        val today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val rfqsByStatus = rfqRepository.findByActiveTrueAndStatusAndTradeDate(statusEnum, today)

        return rfqsByStatus.groupBy { it.client }
            .map { (clientName, rfqs) ->
                val statusCount = rfqs.size.toLong()
                val totalNotional = rfqs.sumOf { it.notionalInLocal }
                ClientStats(
                    clientName = clientName,
                    statusCount = statusCount,
                    totalNotional = totalNotional,
                    averageNotional = if (statusCount > 0) totalNotional / statusCount else 0.0
                )
            }
            .sortedByDescending { it.statusCount }
    }

    override fun getInstrumentStatsByStatus(status: String): List<InstrumentStats>
    {
        val statusEnum = RfqStatus.valueOf(status)
        val today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val rfqsByStatus = rfqRepository.findByActiveTrueAndStatusAndTradeDate(statusEnum, today)
        
        return rfqsByStatus.groupBy { it.underlying }
            .map { (instrument, rfqs) ->
                val statusCount = rfqs.size.toLong()
                val totalNotional = rfqs.sumOf { it.notionalInLocal }
                val averageNotional = if (statusCount > 0) totalNotional / statusCount else 0.0
                InstrumentStats(
                    instrument = instrument,
                    statusCount = statusCount,
                    totalNotional = totalNotional,
                    averageNotional = averageNotional
                )
            }
            .sortedByDescending { it.statusCount }
    }

    override fun getDailyStats(days: Int): List<DailyStats>
    {
        val today = LocalDate.now()
        val dailyStats = mutableListOf<DailyStats>()
        
        for (i in 0 until days) 
        {
            val date = today.minusDays(i.toLong())
            val dateString = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            val rfqsForDate = rfqRepository.findByActiveTrueAndTradeDate(dateString)
            dailyStats.add(
                DailyStats(
                    date = dateString,
                    totalRfqs = rfqsForDate.size.toLong(),
                    statusCounts = RfqStatus.values().associateWith { status ->
                        rfqsForDate.count { it.status == status }.toLong()
                    }
                )
            )
        }
        
        return dailyStats.sortedBy { it.date }
    }

    override fun getClientSuccessRates(): List<ClientSuccessRate> 
    {
        val allActiveRfqs = rfqRepository.findByActiveTrue()
        val clientStats = allActiveRfqs.groupBy { it.client }
            .map { (clientName, rfqs) ->
                val totalRfqs = rfqs.size.toLong()
                val tradeCompletedCount = rfqs.count { it.status == RfqStatus.TRADE_COMPLETED }.toLong()
                val tradedAwayCount = rfqs.count { it.status == RfqStatus.TRADED_AWAY }.toLong()
                val successRate = if (totalRfqs > 0) (tradeCompletedCount.toDouble() / totalRfqs.toDouble()) * 100.0 else 0.0
                val failureRate = if (totalRfqs > 0) (tradedAwayCount.toDouble() / totalRfqs.toDouble()) * 100.0 else 0.0
                
                ClientSuccessRate(
                    clientName = clientName,
                    successRate = successRate,
                    failureRate = failureRate,
                    totalRfqs = totalRfqs
                )
            }
            .sortedByDescending { it.successRate }
        
        return clientStats
    }

    override fun getTodayStats(): RfqStats
    {
        val today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val todayRfqs = rfqRepository.findByActiveTrueAndTradeDate(today)
        val totalRfqs = todayRfqs.size.toLong()
        val statusCounts = RfqStatus.values().associateWith { status ->
            todayRfqs.count { it.status == status }.toLong()
        }
        
        return RfqStats(
            totalRfqs = totalRfqs,
            statusCounts = statusCounts,
        )
    }
}
