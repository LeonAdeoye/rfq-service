package com.leon.rfqservice.repository

import com.leon.rfqservice.model.Rfq
import com.leon.rfqservice.model.enums.RfqStatus
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface RfqRepository : MongoRepository<Rfq, String> 
{
    fun findByStatus(status: RfqStatus): List<Rfq>
    
    fun findByClient(client: String): List<Rfq>
}
