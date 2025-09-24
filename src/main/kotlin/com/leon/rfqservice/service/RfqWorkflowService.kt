package com.leon.rfqservice.service

import com.leon.rfqservice.model.RfqWorkflowEvent

interface RfqWorkflowService 
{
    fun addWorkflowEvent(event: RfqWorkflowEvent): RfqWorkflowEvent
    fun getWorkflowEvents(rfqId: String): List<RfqWorkflowEvent>
}

