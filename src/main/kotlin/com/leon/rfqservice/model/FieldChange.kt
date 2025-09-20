package com.leon.rfqservice.model

import com.fasterxml.jackson.annotation.JsonProperty

data class FieldChange(
    @field:JsonProperty("fieldName")
    val fieldName: String,
    
    @field:JsonProperty("oldValue")
    val oldValue: Any?,
    
    @field:JsonProperty("newValue")
    val newValue: Any?,
    
    @field:JsonProperty("changeType")
    val changeType: String
)
