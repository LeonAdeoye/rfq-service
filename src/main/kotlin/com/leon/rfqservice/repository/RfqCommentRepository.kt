package com.leon.rfqservice.repository

import com.leon.rfqservice.model.RfqComment
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface RfqCommentRepository : MongoRepository<RfqComment, String> 
{
    fun findByRfqIdOrderByTimestampDesc(rfqId: String): List<RfqComment>
    
    fun findByUserIdOrderByTimestampDesc(userId: String): List<RfqComment>
    
    fun findByTimestampBetween(startDate: LocalDateTime, endDate: LocalDateTime): List<RfqComment>
    
    @Query("{'rfqId': ?0, 'type': ?1}")
    fun findByRfqIdAndType(rfqId: String, type: String): List<RfqComment>
}
