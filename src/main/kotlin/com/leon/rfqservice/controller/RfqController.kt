package com.leon.rfqservice.controller

import com.leon.rfqservice.model.Rfq
import com.leon.rfqservice.service.RfqService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/rfq")
class RfqController @Autowired constructor(private val rfqService: RfqService)
{
    private val logger = LoggerFactory.getLogger(RfqController::class.java)

    @PostMapping
    fun createRfq(@RequestBody rfq: Rfq): ResponseEntity<Rfq> 
    {
        logger.info("Received request to create RFQ: ${rfq.rfqId}")
        return try 
        {
            val createdRfq = rfqService.createRfq(rfq)
            ResponseEntity.ok(createdRfq)
        } 
        catch (e: Exception) 
        {
            logger.error("Failed to create RFQ: ${rfq.rfqId}", e)
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{rfqId}")
    fun updateRfq(@PathVariable rfqId: String, @RequestBody updates: Map<String, Any>): ResponseEntity<Rfq> 
    {
        logger.info("Received request to update RFQ: $rfqId")
        return try 
        {
            val updatedRfq = rfqService.updateRfq(rfqId, updates)
            ResponseEntity.ok(updatedRfq)
        } 
        catch (e: Exception) 
        {
            logger.error("Failed to update RFQ: $rfqId", e)
            ResponseEntity.badRequest().build()
        }
    }

    @GetMapping("/{rfqId}")
    fun getRfqById(@PathVariable rfqId: String): ResponseEntity<Rfq> 
    {
        logger.info("Received request to get RFQ by ID: $rfqId")
        return try 
        {
            val rfq = rfqService.getRfqById(rfqId)
            if (rfq != null) 
                ResponseEntity.ok(rfq)
            else 
                ResponseEntity.notFound().build()
        } 
        catch (e: Exception) 
        {
            logger.error("Failed to get RFQ: $rfqId", e)
            ResponseEntity.badRequest().build()
        }
    }

    @GetMapping
    fun getAllRfqs(fromDaysAgo: Int = 1): ResponseEntity<List<Rfq>>
    {
        logger.info("Received request to get all RFQs from $fromDaysAgo days ago.")
        return try 
        {
            val rfqs = rfqService.getAllRfqs(fromDaysAgo)
            ResponseEntity.ok(rfqs)
        } 
        catch (e: Exception) 
        {
            logger.error("Failed to get all RFQs from $fromDaysAgo days ago.", e)
            ResponseEntity.badRequest().build()
        }
    }

    @DeleteMapping("/{rfqId}")
    fun deleteRfq(@PathVariable rfqId: String): ResponseEntity<Void> 
    {
        logger.info("Received request to delete RFQ: $rfqId")
        return try 
        {
            val deleted = rfqService.deleteRfq(rfqId)
            if (deleted) 
                ResponseEntity.ok().build()
            else 
                ResponseEntity.badRequest().build()
        } 
        catch (e: Exception) 
        {
            logger.error("Failed to delete RFQ: $rfqId", e)
            ResponseEntity.badRequest().build()
        }
    }
}
