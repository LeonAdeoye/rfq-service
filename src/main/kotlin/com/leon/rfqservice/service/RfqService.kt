package com.leon.rfqservice.service

import com.leon.rfqservice.model.Rfq
import com.leon.rfqservice.model.enums.RfqStatus

interface RfqService 
{
    fun createRfq(rfq: Rfq): Rfq
    
    fun updateRfq(rfqId: String, updates: Map<String, Any>): Rfq
    
    fun getRfqById(rfqId: String): Rfq?
    
    fun getAllRfqs(): List<Rfq>
    
    fun getRfqsByStatus(status: RfqStatus): List<Rfq>
    
    fun getRfqsByClient(client: String): List<Rfq>
    
    fun getRfqsByAssignedTo(assignedTo: String): List<Rfq>
    
    fun deleteRfq(rfqId: String): Boolean
    
    fun updateRfqStatus(rfqId: String, status: RfqStatus, userId: String, comment: String? = null): Rfq
    
    fun assignRfq(rfqId: String, assignedTo: String, userId: String): Rfq
}
