package com.leon.rfqservice.repository

import com.leon.rfqservice.model.RfqWorkflowEvent
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface RfqWorkflowEventRepository : MongoRepository<RfqWorkflowEvent, String> 
{
    fun findByRfqIdOrderByTimestampDesc(rfqId: String): List<RfqWorkflowEvent>
    
    fun findByRfqIdAndEventTypeOrderByTimestampDesc(rfqId: String, eventType: String): List<RfqWorkflowEvent>
    
    fun findByUserIdOrderByTimestampDesc(userId: String): List<RfqWorkflowEvent>
    
    fun findByTimestampBetween(startDate: LocalDateTime, endDate: LocalDateTime): List<RfqWorkflowEvent>
    
    @Query("{'rfqId': ?0, 'eventType': {\$in: ?1}}")
    fun findByRfqIdAndEventTypeIn(rfqId: String, eventTypes: List<String>): List<RfqWorkflowEvent>
}
