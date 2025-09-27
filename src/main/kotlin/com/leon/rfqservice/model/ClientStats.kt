package com.leon.rfqservice.model

import com.fasterxml.jackson.annotation.JsonProperty

data class ClientStats(
    @field:JsonProperty("clientName")
    val clientName: String,

    @field:JsonProperty("statusCount")
    val statusCount: Long,

    @field:JsonProperty("totalNotional")
    val totalNotional: Double,

    @field:JsonProperty("averageNotional")
    val averageNotional: Double
)
