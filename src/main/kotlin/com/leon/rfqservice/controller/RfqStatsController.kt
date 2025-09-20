package com.leon.rfqservice.controller

import com.leon.rfqservice.model.RfqStats
import com.leon.rfqservice.model.DailyStats
import com.leon.rfqservice.model.ClientSuccessRate
import com.leon.rfqservice.service.RfqStatsService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/rfq/stats")
class RfqStatsController @Autowired constructor(private val statsService: RfqStatsService)
{
    private val logger = LoggerFactory.getLogger(RfqStatsController::class.java)

    @GetMapping("/client/{client}")
    fun getRfqStatsByClient(@PathVariable client: String): ResponseEntity<RfqStats> 
    {
        logger.info("Received request to get RFQ stats by client: $client")
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
        logger.info("Received request to get RFQ stats by status: $status")
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
        logger.info("Received request to get RFQ stats for $days days")
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
        logger.info("Received request to get client success rates")
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
