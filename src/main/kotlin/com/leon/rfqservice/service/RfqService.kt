package com.leon.rfqservice.service

import com.leon.rfqservice.model.Rfq

interface RfqService 
{
    fun createRfq(rfq: Rfq): Rfq
    
    fun updateRfq(rfqId: String, updates: Map<String, Any>): Rfq
    
    fun getRfqById(rfqId: String): Rfq?
    
    fun getAllRfqs(): List<Rfq>
    
    fun deleteRfq(rfqId: String): Boolean
}
