package com.leon.rfqservice.service

import com.leon.rfqservice.model.RfqWorkflowEvent
import com.leon.rfqservice.model.RfqComment
import com.leon.rfqservice.model.enums.RfqStatus
import com.leon.rfqservice.model.enums.WorkflowAction

interface RfqWorkflowService 
{
    fun addWorkflowEvent(event: RfqWorkflowEvent): RfqWorkflowEvent
    fun getWorkflowEvents(rfqId: String): List<RfqWorkflowEvent>
    fun addComment(comment: RfqComment): RfqComment
    fun getComments(rfqId: String): List<RfqComment>
    fun processWorkflowAction(rfqId: String, action: WorkflowAction, userId: String, comment: String? = null, fieldChanges: Map<String, Any> = emptyMap()): RfqWorkflowEvent
}

