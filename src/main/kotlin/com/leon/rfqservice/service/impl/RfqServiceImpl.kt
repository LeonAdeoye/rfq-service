package com.leon.rfqservice.service.impl

import com.leon.rfqservice.model.Rfq
import com.leon.rfqservice.model.enums.RfqStatus
import com.leon.rfqservice.repository.RfqRepository
import com.leon.rfqservice.service.RfqService
import com.leon.rfqservice.service.RfqWorkflowService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class RfqServiceImpl @Autowired constructor(
    private val rfqRepository: RfqRepository,
    private val workflowService: RfqWorkflowService
) : RfqService 
{
    private val logger = LoggerFactory.getLogger(RfqServiceImpl::class.java)

    override fun createRfq(rfq: Rfq): Rfq 
    {
        // TODO: Implement RFQ creation logic
        logger.info("Creating RFQ: ${rfq.rfqId}")
        return rfqRepository.save(rfq)
    }

    override fun updateRfq(rfqId: String, updates: Map<String, Any>): Rfq 
    {
        // TODO: Implement RFQ update logic
        logger.info("Updating RFQ: $rfqId")
        val existingRfq = rfqRepository.findById(rfqId).orElseThrow { 
            RuntimeException("RFQ not found: $rfqId") 
        }
        
        // Apply updates to existing RFQ
        val updatedRfq = existingRfq.copy(
            updatedAt = LocalDateTime.now()
        )
        
        return rfqRepository.save(updatedRfq)
    }

    override fun getRfqById(rfqId: String): Rfq? 
    {
        // TODO: Implement get RFQ by ID logic
        logger.info("Getting RFQ by ID: $rfqId")
        return rfqRepository.findById(rfqId).orElse(null)
    }

    override fun getAllRfqs(): List<Rfq> 
    {
        // TODO: Implement get all RFQs logic
        logger.info("Getting all RFQs")
        return rfqRepository.findAll()
    }

    override fun getRfqsByStatus(status: RfqStatus): List<Rfq> 
    {
        // TODO: Implement get RFQs by status logic
        logger.info("Getting RFQs by status: $status")
        return rfqRepository.findByStatus(status)
    }

    override fun getRfqsByClient(client: String): List<Rfq> 
    {
        // TODO: Implement get RFQs by client logic
        logger.info("Getting RFQs by client: $client")
        return rfqRepository.findByClient(client)
    }

    override fun getRfqsByAssignedTo(assignedTo: String): List<Rfq> 
    {
        // TODO: Implement get RFQs by assigned to logic
        logger.info("Getting RFQs by assigned to: $assignedTo")
        return rfqRepository.findByAssignedTo(assignedTo)
    }

    override fun deleteRfq(rfqId: String): Boolean 
    {
        // TODO: Implement delete RFQ logic
        logger.info("Deleting RFQ: $rfqId")
        return try 
        {
            rfqRepository.deleteById(rfqId)
            true
        } 
        catch (e: Exception) 
        {
            logger.error("Failed to delete RFQ: $rfqId", e)
            false
        }
    }

    override fun updateRfqStatus(rfqId: String, status: RfqStatus, userId: String, comment: String?): Rfq 
    {
        // TODO: Implement update RFQ status logic
        logger.info("Updating RFQ status: $rfqId to $status")
        val existingRfq = rfqRepository.findById(rfqId).orElseThrow { 
            RuntimeException("RFQ not found: $rfqId") 
        }
        
        val updatedRfq = existingRfq.copy(
            status = status,
            lastActivity = LocalDateTime.now().toString(),
            updatedAt = LocalDateTime.now()
        )
        
        val savedRfq = rfqRepository.save(updatedRfq)
        
        // Add workflow event
        workflowService.processWorkflowAction(
            rfqId, 
            com.leon.rfqservice.model.enums.WorkflowAction.valueOf(status.name), 
            userId, 
            comment
        )
        
        return savedRfq
    }

    override fun assignRfq(rfqId: String, assignedTo: String, userId: String): Rfq 
    {
        // TODO: Implement assign RFQ logic
        logger.info("Assigning RFQ: $rfqId to $assignedTo")
        val existingRfq = rfqRepository.findById(rfqId).orElseThrow { 
            RuntimeException("RFQ not found: $rfqId") 
        }
        
        val updatedRfq = existingRfq.copy(
            assignedTo = assignedTo,
            lastActivity = LocalDateTime.now().toString(),
            updatedAt = LocalDateTime.now()
        )
        
        val savedRfq = rfqRepository.save(updatedRfq)
        
        // Add workflow event
        workflowService.processWorkflowAction(
            rfqId, 
            com.leon.rfqservice.model.enums.WorkflowAction.ASSIGN, 
            userId, 
            "Assigned to $assignedTo"
        )
        
        return savedRfq
    }
}
