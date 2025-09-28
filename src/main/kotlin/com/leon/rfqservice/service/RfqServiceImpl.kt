package com.leon.rfqservice.service

import com.leon.rfqservice.model.Rfq
import com.leon.rfqservice.repository.RfqRepository
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.annotation.PostConstruct
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class RfqServiceImpl @Autowired constructor(private val rfqRepository: RfqRepository, private val objectMapper: ObjectMapper, private val ampsService: AmpsService) : RfqService
{
    private val logger = LoggerFactory.getLogger(RfqServiceImpl::class.java)
    private val rfqCache = mutableMapOf<String, Rfq>()

    @PostConstruct
    fun init()
    {
        logger.info("RFQ Service initialized")
        val today = LocalDate.now().format(DateTimeFormatter.ofPattern("M/d/yyyy"))
        val activeRfqs = rfqRepository.findByActiveTrueAndTradeDate(today)
        logger.info("Loaded ${activeRfqs.size} active RFQs for today ($today)")
        activeRfqs.forEach { rfqCache[it.rfqId] = it }
    }

    override fun createRfq(rfq: Rfq): Rfq 
    {
        ampsService.publishRfqUpdate(rfq)
        rfqCache[rfq.rfqId] = rfq
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
        val savedRfq = rfqRepository.save(updatedRfq)
        rfqCache[rfqId] = savedRfq
        ampsService.publishRfqUpdate(savedRfq)
        return savedRfq;
    }

    override fun getRfqById(rfqId: String): Rfq?
    {
        return if(!rfqCache.containsKey(rfqId))
        {
            val rfq = rfqRepository.findById(rfqId).orElse(null)
            if(rfq != null)
                rfqCache[rfqId] = rfq
            rfq
        }
        else
            rfqCache[rfqId]
    }
    override fun getAllRfqs(): List<Rfq>
    {
        return if(rfqCache.isEmpty())
        {
            val rfqs = rfqRepository.findByActiveTrueAndTradeDate(LocalDate.now().format(DateTimeFormatter.ofPattern("M/d/yyyy")))
            rfqs.forEach { rfqCache[it.rfqId] = it }
            rfqs
        }
        else
            rfqCache.values.toList()
    }

    override fun deleteRfq(rfqId: String): Boolean
    {
        return try
        {

            logger.info("Deleting RFQ: $rfqId")
            val rfq = rfqRepository.findById(rfqId).orElse(null)
            if (rfq != null)
            {
                val deletedRfq = rfq.copy(active = false, updatedAt = LocalDateTime.now())
                rfqRepository.save(deletedRfq)
                rfqCache.remove(rfqId)
                ampsService.publishRfqUpdate(deletedRfq)
                true
            }
            else
            {
                logger.warn("RFQ not found: $rfqId")
                false
            }
        }
        catch (e: Exception)
        {
            logger.error("Failed to delete RFQ: $rfqId", e)
            false
        }
    }
}
