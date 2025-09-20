package com.leon.rfqservice.controller

import com.leon.rfqservice.model.Rfq
import com.leon.rfqservice.model.enums.RfqStatus
import com.leon.rfqservice.service.RfqService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/rfq")
class RfqController @Autowired constructor(
    private val rfqService: RfqService
) 
{
    private val logger = LoggerFactory.getLogger(RfqController::class.java)

    @PostMapping
    fun createRfq(@RequestBody rfq: Rfq): ResponseEntity<Rfq> 
    {
        // TODO: Implement create RFQ endpoint
        logger.info("Creating RFQ: ${rfq.rfqId}")
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
        // TODO: Implement update RFQ endpoint
        logger.info("Updating RFQ: $rfqId")
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
        // TODO: Implement get RFQ by ID endpoint
        logger.info("Getting RFQ by ID: $rfqId")
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
    fun getAllRfqs(): ResponseEntity<List<Rfq>> 
    {
        // TODO: Implement get all RFQs endpoint
        logger.info("Getting all RFQs")
        return try 
        {
            val rfqs = rfqService.getAllRfqs()
            ResponseEntity.ok(rfqs)
        } 
        catch (e: Exception) 
        {
            logger.error("Failed to get all RFQs", e)
            ResponseEntity.badRequest().build()
        }
    }

    @GetMapping("/status/{status}")
    fun getRfqsByStatus(@PathVariable status: String): ResponseEntity<List<Rfq>> 
    {
        // TODO: Implement get RFQs by status endpoint
        logger.info("Getting RFQs by status: $status")
        return try 
        {
            val statusEnum = RfqStatus.valueOf(status.uppercase())
            val rfqs = rfqService.getRfqsByStatus(statusEnum)
            ResponseEntity.ok(rfqs)
        } 
        catch (e: Exception) 
        {
            logger.error("Failed to get RFQs by status: $status", e)
            ResponseEntity.badRequest().build()
        }
    }

    @GetMapping("/client/{client}")
    fun getRfqsByClient(@PathVariable client: String): ResponseEntity<List<Rfq>> 
    {
        // TODO: Implement get RFQs by client endpoint
        logger.info("Getting RFQs by client: $client")
        return try 
        {
            val rfqs = rfqService.getRfqsByClient(client)
            ResponseEntity.ok(rfqs)
        } 
        catch (e: Exception) 
        {
            logger.error("Failed to get RFQs by client: $client", e)
            ResponseEntity.badRequest().build()
        }
    }

    @GetMapping("/assigned/{assignedTo}")
    fun getRfqsByAssignedTo(@PathVariable assignedTo: String): ResponseEntity<List<Rfq>> 
    {
        // TODO: Implement get RFQs by assigned to endpoint
        logger.info("Getting RFQs by assigned to: $assignedTo")
        return try 
        {
            val rfqs = rfqService.getRfqsByAssignedTo(assignedTo)
            ResponseEntity.ok(rfqs)
        } 
        catch (e: Exception) 
        {
            logger.error("Failed to get RFQs by assigned to: $assignedTo", e)
            ResponseEntity.badRequest().build()
        }
    }

    @DeleteMapping("/{rfqId}")
    fun deleteRfq(@PathVariable rfqId: String): ResponseEntity<Void> 
    {
        // TODO: Implement delete RFQ endpoint
        logger.info("Deleting RFQ: $rfqId")
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
