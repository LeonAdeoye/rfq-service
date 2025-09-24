package com.leon.rfqservice.service

import com.leon.rfqservice.model.Rfq
import com.leon.rfqservice.model.enums.RfqStatus
import com.leon.rfqservice.repository.RfqRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class RfqServiceImpl @Autowired constructor(
    private val rfqRepository: RfqRepository, 
    private val workflowService: RfqWorkflowService,
    private val objectMapper: ObjectMapper
) : RfqService
{
    private val logger = LoggerFactory.getLogger(RfqServiceImpl::class.java)

    override fun createRfq(rfq: Rfq): Rfq 
    {
        return rfqRepository.save(rfq)
    }

    override fun updateRfq(rfqId: String, updates: Map<String, Any>): Rfq 
    {
        val existingRfq = rfqRepository.findById(rfqId).orElseThrow {
            RuntimeException("RFQ not found: $rfqId") 
        }

        @Suppress("UNCHECKED_CAST")
        val rfqMap = objectMapper.convertValue(existingRfq, Map::class.java) as MutableMap<String, Any>
        rfqMap.putAll(updates)
        rfqMap["lastActivity"] = LocalDateTime.now().toString()
        rfqMap["updatedAt"] = LocalDateTime.now()
        val updatedRfq = objectMapper.convertValue(rfqMap, Rfq::class.java)
        return rfqRepository.save(updatedRfq)
    }

    override fun getRfqById(rfqId: String): Rfq? 
    {
        return rfqRepository.findById(rfqId).orElse(null)
    }

    override fun getAllRfqs(fromDaysAgo: Int): List<Rfq>
    {
        return rfqRepository.findAll()
    }

    override fun getRfqsByStatus(status: RfqStatus): List<Rfq> 
    {
        return rfqRepository.findByStatus(status)
    }

    override fun getRfqsByClient(client: String): List<Rfq> 
    {
        return rfqRepository.findByClient(client)
    }

    override fun getRfqsByAssignedTo(assignedTo: String): List<Rfq> 
    {
        return rfqRepository.findByAssignedTo(assignedTo)
    }

    override fun deleteRfq(rfqId: String): Boolean 
    {
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
        val existingRfq = rfqRepository.findById(rfqId).orElseThrow {
            RuntimeException("RFQ not found: $rfqId")
        }
        val updatedRfq = existingRfq.copy(status = status, lastActivity = LocalDateTime.now().toString(), updatedAt = LocalDateTime.now())
        val savedRfq = rfqRepository.save(updatedRfq)
        workflowService.processWorkflowAction(rfqId, com.leon.rfqservice.model.enums.WorkflowAction.valueOf(status.name), userId, comment)
        return savedRfq
    }

    override fun assignRfq(rfqId: String, assignedTo: String, userId: String): Rfq 
    {
        val existingRfq = rfqRepository.findById(rfqId).orElseThrow {
            RuntimeException("RFQ not found: $rfqId")
        }
        
        val updatedRfq = existingRfq.copy(assignedTo = assignedTo, lastActivity = LocalDateTime.now().toString(), updatedAt = LocalDateTime.now())
        val savedRfq = rfqRepository.save(updatedRfq)
        workflowService.processWorkflowAction(rfqId, com.leon.rfqservice.model.enums.WorkflowAction.ASSIGNMENT, userId,            "Assigned to $assignedTo")
        return savedRfq
    }
}
