package com.leon.rfqservice.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.leon.rfqservice.model.enums.RfqStatus

data class InstrumentStats(
    @field:JsonProperty("instrument")
    val instrument: String,
    
    @field:JsonProperty("statusCount")
    val statusCount: Long,
    
    @field:JsonProperty("totalNotional")
    val totalNotional: Double,
    
    @field:JsonProperty("averageNotional")
    val averageNotional: Double,

    @field:JsonProperty("totalSalesCreditAmount")
    val totalSalesCreditAmount: Double,

    @field:JsonProperty("averageSalesCreditAmount")
    val averageSalesCreditAmount: Double,

    @field:JsonProperty("status")
    val status: RfqStatus
)
