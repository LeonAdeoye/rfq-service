package com.leon.rfqservice.service

import com.leon.rfqservice.model.Rfq
import com.leon.rfqservice.model.RfqWorkflowEvent

interface AmpsService 
{
    fun publishRfqUpdate(rfq: Rfq)
    
    fun publishWorkflowEvent(event: RfqWorkflowEvent)
    
    fun subscribeToRfqUpdates()
    
    fun subscribeToWorkflowEvents()
}
