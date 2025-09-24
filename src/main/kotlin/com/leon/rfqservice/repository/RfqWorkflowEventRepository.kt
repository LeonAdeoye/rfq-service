package com.leon.rfqservice.repository

import com.leon.rfqservice.model.RfqWorkflowEvent
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface RfqWorkflowEventRepository : MongoRepository<RfqWorkflowEvent, String> 
{
    fun findByRfqIdOrderByTimestampDesc(rfqId: String): List<RfqWorkflowEvent>
}
