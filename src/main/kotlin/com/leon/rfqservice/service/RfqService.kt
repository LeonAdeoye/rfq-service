package com.leon.rfqservice.service

import com.leon.rfqservice.model.Rfq
import com.leon.rfqservice.model.enums.RfqStatus

interface RfqService 
{
    fun createRfq(rfq: Rfq): Rfq
    
    fun updateRfq(rfqId: String, updates: Map<String, Any>): Rfq
    
    fun getRfqById(rfqId: String): Rfq?
    
    fun getAllRfqs(fromDaysAgo: Int): List<Rfq>
    
    fun deleteRfq(rfqId: String): Boolean
}
