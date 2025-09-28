package com.leon.rfqservice.model

import com.fasterxml.jackson.annotation.JsonProperty

data class ClientPercentages(
    @field:JsonProperty("clientName")
    val clientName: String,

    @field:JsonProperty("TradeCompletedPercent")
    val tradeCompletedPercent: Double,

    @field:JsonProperty("tradedAwayPercent")
    val tradedAwayPrecent: Double,

    @field:JsonProperty("othersPercent")
    val othersPercent: Double,

    @field:JsonProperty("totalRfqs")
    val totalRfqs: Long
)
