package com.leon.rfqservice.model

import com.fasterxml.jackson.annotation.JsonProperty

data class ClientSuccessRate(
    @field:JsonProperty("clientName")
    val clientName: String,
    
    @field:JsonProperty("TradeCompletedRate")
    val tradeCompletedRate: Double,

    @field:JsonProperty("tradedAwayRate")
    val tradedAwayRate: Double,

    @field:JsonProperty("othersRate")
    val othersRate: Double,

    @field:JsonProperty("totalRfqs")
    val totalRfqs: Long
)
