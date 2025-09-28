package com.leon.rfqservice.repository

import com.leon.rfqservice.model.Rfq
import com.leon.rfqservice.model.enums.RfqStatus
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface RfqRepository : MongoRepository<Rfq, String> 
{
    fun findByActiveTrue(): List<Rfq>
    
    fun findByActiveTrueAndTradeDate(tradeDate: String): List<Rfq>
    
    fun findByActiveTrueAndClientAndTradeDate(client: String, tradeDate: String): List<Rfq>
    
    fun findByActiveTrueAndStatusAndTradeDate(status: RfqStatus, tradeDate: String): List<Rfq>
    fun findByActiveTrueAndTradeDateGreaterThanEqual(tradeDate: String): List<Rfq>
}
