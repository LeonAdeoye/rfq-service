package com.leon.rfqservice.controller

import com.leon.rfqservice.model.RfqStats
import com.leon.rfqservice.model.DailyStats
import com.leon.rfqservice.model.ClientPercentages
import com.leon.rfqservice.model.ClientStats
import com.leon.rfqservice.model.InstrumentStats
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
    fun getStatsByClient(@PathVariable client: String): ResponseEntity<RfqStats> 
    {
        logger.info("Received request to get RFQ stats by client: $client")
        return try 
        {
            val stats = statsService.getStatsByClient(client)
            ResponseEntity.ok(stats)
        } 
        catch (e: Exception) 
        {
            logger.error("Failed to get RFQ stats by client: $client", e)
            ResponseEntity.badRequest().build()
        }
    }

    @GetMapping("/clients/{status}")
    fun getClientStatsByStatus(@PathVariable status: String): ResponseEntity<List<ClientStats>> 
    {
        logger.info("Received request to get client stats by status: $status")
        return try 
        {
            val stats = statsService.getClientStatsByStatus(status)
            ResponseEntity.ok(stats)
        } 
        catch (e: Exception) 
        {
            logger.error("Failed to get client stats by status: $status", e)
            ResponseEntity.badRequest().build()
        }
    }

    @GetMapping("/instruments/{status}")
    fun getInstrumentStatsByStatus(@PathVariable status: String): ResponseEntity<List<InstrumentStats>> 
    {
        logger.info("Received request to get instrument stats by status: $status")
        return try 
        {
            val stats = statsService.getInstrumentStatsByStatus(status)
            ResponseEntity.ok(stats)
        } 
        catch (e: Exception) 
        {
            logger.error("Failed to get instrument stats by status: $status", e)
            ResponseEntity.badRequest().build()
        }
    }

    @GetMapping("/daily")
    fun getDailyStats(@RequestParam(defaultValue = "30") days: Int): ResponseEntity<List<DailyStats>> 
    {
        logger.info("Received request to get RFQ stats for $days days")
        return try 
        {
            val stats = statsService.getDailyStats(days)
            ResponseEntity.ok(stats)
        } 
        catch (e: Exception) 
        {
            logger.error("Failed to get daily RFQ stats for $days days", e)
            ResponseEntity.badRequest().build()
        }
    }

    @GetMapping("/client-percentages/{fromTradeDate}") // mm-dd-yyyy
    fun getClientPercentages(@PathVariable fromTradeDate: String): ResponseEntity<List<ClientPercentages>>
    {
        logger.info("Received request to get client percentages from trade date $fromTradeDate")
        return try 
        {
            val successRates = statsService.getClientPercentages(fromTradeDate)
            ResponseEntity.ok(successRates)
        } 
        catch (e: Exception) 
        {
            logger.error("Failed to get client percentages", e)
            ResponseEntity.badRequest().build()
        }
    }

    @GetMapping("/today")
    fun getTodayStats(): ResponseEntity<RfqStats> 
    {
        logger.info("Received request to get today's RFQ stats")
        return try 
        {
            val stats = statsService.getTodayStats()
            ResponseEntity.ok(stats)
        } 
        catch (e: Exception) 
        {
            logger.error("Failed to get today's RFQ stats", e)
            ResponseEntity.badRequest().build()
        }
    }
}
