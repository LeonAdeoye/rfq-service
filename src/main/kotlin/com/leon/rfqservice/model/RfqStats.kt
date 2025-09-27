package com.leon.rfqservice.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.leon.rfqservice.model.enums.RfqStatus

data class RfqStats(
    @field:JsonProperty("totalRfqs")
    val totalRfqs: Long,
    
    @field:JsonProperty("statusCounts")
    val statusCounts: Map<RfqStatus, Long>
)

