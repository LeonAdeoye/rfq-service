package com.leon.rfqservice.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "rfq_comments")
data class RfqComment(
    @Id
    @field:JsonProperty("id")
    val id: String,
    
    @field:JsonProperty("rfqId")
    val rfqId: String,
    
    @field:JsonProperty("userId")
    val userId: String,
    
    @field:JsonProperty("timestamp")
    val timestamp: LocalDateTime,
    
    @field:JsonProperty("comment")
    val comment: String,
    
    @field:JsonProperty("type")
    val type: String = "COMMENT"
)
