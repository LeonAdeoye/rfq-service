package com.leon.rfqservice.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.leon.rfqservice.model.enums.RfqSide

data class RfqLeg(
    @field:JsonProperty("quantity")
    val quantity: Int,
    
    @field:JsonProperty("strike")
    val strike: Double,
    
    @field:JsonProperty("optionType")
    val optionType: String, // CALL or PUT
    
    @field:JsonProperty("side")
    val side: RfqSide,
    
    @field:JsonProperty("volatility")
    val volatility: Double,
    
    @field:JsonProperty("interestRate")
    val interestRate: Double
)
