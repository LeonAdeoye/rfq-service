package com.leon.rfqservice.repository

import com.leon.rfqservice.model.Rfq
import com.leon.rfqservice.model.enums.RfqStatus
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface RfqRepository : MongoRepository<Rfq, String> 
{
    fun findByStatus(status: RfqStatus): List<Rfq>
    
    fun findByClient(client: String): List<Rfq>
    
    fun findByAssignedTo(assignedTo: String): List<Rfq>
    
    fun findByCreatedAtBetween(startDate: LocalDateTime, endDate: LocalDateTime): List<Rfq>
    
    @Query("{'status': ?0, 'client': ?1}")
    fun findByStatusAndClient(status: RfqStatus, client: String): List<Rfq>
    
    @Query("{'status': {\$in: ?0}}")
    fun findByStatusIn(statuses: List<RfqStatus>): List<Rfq>
    
    fun countByStatus(status: RfqStatus): Long
    
    fun countByClient(client: String): Long
}
