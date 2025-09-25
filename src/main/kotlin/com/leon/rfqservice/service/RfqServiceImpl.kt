package com.leon.rfqservice.service

import com.leon.rfqservice.model.Rfq
import com.leon.rfqservice.repository.RfqRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class RfqServiceImpl @Autowired constructor(private val rfqRepository: RfqRepository, private val objectMapper: ObjectMapper, private val ampsService: AmpsService) : RfqService
{
    private val logger = LoggerFactory.getLogger(RfqServiceImpl::class.java)

    override fun createRfq(rfq: Rfq): Rfq 
    {
        ampsService.publishRfqUpdate(rfq)
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
        ampsService.publishRfqUpdate(updatedRfq)
        val savedRfq = rfqRepository.save(updatedRfq)
        ampsService.publishRfqUpdate(updatedRfq)
        return savedRfq;
    }

    override fun getRfqById(rfqId: String): Rfq? = rfqRepository.findById(rfqId).orElse(null)
    override fun getAllRfqs(fromDaysAgo: Int): List<Rfq> = rfqRepository.findAll()

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
}
