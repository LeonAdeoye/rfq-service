package com.leon.rfqservice.controller

import com.leon.rfqservice.model.RfqStats
import com.leon.rfqservice.service.RfqStatsService
import com.leon.rfqservice.service.DailyStats
import com.leon.rfqservice.service.ClientSuccessRate
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/rfq/stats")
class RfqStatsController @Autowired constructor(
    private val statsService: RfqStatsService
) 
{
    private val logger = LoggerFactory.getLogger(RfqStatsController::class.java)

    @GetMapping
    fun getRfqStats(): ResponseEntity<RfqStats> 
    {
        // TODO: Implement get RFQ stats endpoint
        logger.info("Getting RFQ stats")
        return try 
        {
            val stats = statsService.getRfqStats()
            ResponseEntity.ok(stats)
        } 
        catch (e: Exception) 
        {
            logger.error("Failed to get RFQ stats", e)
            ResponseEntity.badRequest().build()
        }
    }

    @GetMapping("/date-range")
    fun getRfqStatsByDateRange(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) startDate: LocalDateTime,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) endDate: LocalDateTime
    ): ResponseEntity<RfqStats> 
    {
        // TODO: Implement get RFQ stats by date range endpoint
        logger.info("Getting RFQ stats by date range: $startDate to $endDate")
        return try 
        {
            val stats = statsService.getRfqStatsByDateRange(startDate, endDate)
            ResponseEntity.ok(stats)
        } 
        catch (e: Exception) 
        {
            logger.error("Failed to get RFQ stats by date range: $startDate to $endDate", e)
            ResponseEntity.badRequest().build()
        }
    }

    @GetMapping("/client/{client}")
    fun getRfqStatsByClient(@PathVariable client: String): ResponseEntity<RfqStats> 
    {
        // TODO: Implement get RFQ stats by client endpoint
        logger.info("Getting RFQ stats by client: $client")
        return try 
        {
            val stats = statsService.getRfqStatsByClient(client)
            ResponseEntity.ok(stats)
        } 
        catch (e: Exception) 
        {
            logger.error("Failed to get RFQ stats by client: $client", e)
            ResponseEntity.badRequest().build()
        }
    }

    @GetMapping("/status/{status}")
    fun getRfqStatsByStatus(@PathVariable status: String): ResponseEntity<RfqStats> 
    {
        // TODO: Implement get RFQ stats by status endpoint
        logger.info("Getting RFQ stats by status: $status")
        return try 
        {
            val stats = statsService.getRfqStatsByStatus(status)
            ResponseEntity.ok(stats)
        } 
        catch (e: Exception) 
        {
            logger.error("Failed to get RFQ stats by status: $status", e)
            ResponseEntity.badRequest().build()
        }
    }

    @GetMapping("/daily")
    fun getDailyRfqStats(@RequestParam(defaultValue = "30") days: Int): ResponseEntity<List<DailyStats>> 
    {
        // TODO: Implement get daily RFQ stats endpoint
        logger.info("Getting daily RFQ stats for $days days")
        return try 
        {
            val stats = statsService.getDailyRfqStats(days)
            ResponseEntity.ok(stats)
        } 
        catch (e: Exception) 
        {
            logger.error("Failed to get daily RFQ stats for $days days", e)
            ResponseEntity.badRequest().build()
        }
    }

    @GetMapping("/client-success-rates")
    fun getClientSuccessRates(): ResponseEntity<List<ClientSuccessRate>> 
    {
        // TODO: Implement get client success rates endpoint
        logger.info("Getting client success rates")
        return try 
        {
            val successRates = statsService.getClientSuccessRates()
            ResponseEntity.ok(successRates)
        } 
        catch (e: Exception) 
        {
            logger.error("Failed to get client success rates", e)
            ResponseEntity.badRequest().build()
        }
    }
}
