package com.leon.rfqservice.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.leon.rfqservice.model.enums.RfqStatus
import com.leon.rfqservice.model.enums.WorkflowAction
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "rfq_workflow_events")
data class RfqWorkflowEvent(
    @Id
    @field:JsonProperty("id")
    val id: String,
    
    @field:JsonProperty("rfqId")
    val rfqId: String,
    
    @field:JsonProperty("eventType")
    val eventType: WorkflowAction,
    
    @field:JsonProperty("fromStatus")
    val fromStatus: RfqStatus?,
    
    @field:JsonProperty("toStatus")
    val toStatus: RfqStatus?,
    
    @field:JsonProperty("userId")
    val userId: String,
    
    @field:JsonProperty("timestamp")
    val timestamp: LocalDateTime,
    
    @field:JsonProperty("comment")
    val comment: String?,
    
    @field:JsonProperty("fieldChanges")
    val fieldChanges: Map<String, FieldChange> = emptyMap()
)

