package com.leon.rfqservice.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.leon.rfqservice.model.enums.RfqStatus

data class StatusAggregates(
    @field:JsonProperty("count")
    val count: Long,
    
    @field:JsonProperty("totalNotionalInUSD")
    val totalNotionalInUSD: Double,
    
    @field:JsonProperty("totalSalesCreditAmount")
    val totalSalesCreditAmount: Double,
    
    @field:JsonProperty("averageNotionalInUSD")
    val averageNotionalInUSD: Double,
    
    @field:JsonProperty("averageSalesCreditAmount")
    val averageSalesCreditAmount: Double
)

data class RfqStats(
    @field:JsonProperty("totalRfqs")
    val totalRfqs: Long,
    
    @field:JsonProperty("statusAggregates")
    val statusAggregates: Map<RfqStatus, StatusAggregates>
)

