package com.leon.rfqservice.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.leon.rfqservice.model.enums.RfqStatus
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "rfqs")
data class Rfq(
    @Id
    @field:JsonProperty("rfqId")
    val rfqId: String,
    
    @field:JsonProperty("arrivalTime")
    val arrivalTime: String,
    
    @field:JsonProperty("underlying")
    val underlying: String,
    
    @field:JsonProperty("strike")
    val strike: String,
    
    @field:JsonProperty("underlyingPrice")
    val underlyingPrice: Double,
    
    @field:JsonProperty("request")
    val request: String,
    
    @field:JsonProperty("client")
    val client: String,
    
    @field:JsonProperty("status")
    val status: RfqStatus,
    
    @field:JsonProperty("bookCode")
    val bookCode: String,
    
    @field:JsonProperty("notionalInUSD")
    val notionalInUSD: String,
    
    @field:JsonProperty("notionalInLocal")
    val notionalInLocal: Double,
    
    @field:JsonProperty("notionalCurrency")
    val notionalCurrency: String,
    
    @field:JsonProperty("notionalFXRate")
    val notionalFXRate: Double,
    
    @field:JsonProperty("volatility")
    val volatility: Double,
    
    @field:JsonProperty("interestRate")
    val interestRate: Double,
    
    @field:JsonProperty("exerciseType")
    val exerciseType: String,
    
    @field:JsonProperty("dayCountConvention")
    val dayCountConvention: Int,
    
    @field:JsonProperty("tradeDate")
    val tradeDate: String,
    
    @field:JsonProperty("maturityDate")
    val maturityDate: String,
    
    @field:JsonProperty("daysToExpiry")
    val daysToExpiry: Int,
    
    @field:JsonProperty("multiplier")
    val multiplier: Int,
    
    @field:JsonProperty("contracts")
    val contracts: Int,
    
    @field:JsonProperty("salesCreditPercentage")
    val salesCreditPercentage: Double,
    
    @field:JsonProperty("salesCreditAmount")
    val salesCreditAmount: String,
    
    @field:JsonProperty("premiumSettlementFXRate")
    val premiumSettlementFXRate: Double,
    
    @field:JsonProperty("premiumSettlementDaysOverride")
    val premiumSettlementDaysOverride: Int,
    
    @field:JsonProperty("premiumSettlementCurrency")
    val premiumSettlementCurrency: String,
    
    @field:JsonProperty("premiumSettlementDate")
    val premiumSettlementDate: String,
    
    @field:JsonProperty("askImpliedVol")
    val askImpliedVol: Double,
    
    @field:JsonProperty("impliedVol")
    val impliedVol: Double,
    
    @field:JsonProperty("bidImpliedVol")
    val bidImpliedVol: Double,
    
    @field:JsonProperty("spread")
    val spread: Double,
    
    @field:JsonProperty("askPremiumInLocal")
    val askPremiumInLocal: String,
    
    @field:JsonProperty("premiumInUSD")
    val premiumInUSD: String,
    
    @field:JsonProperty("premiumInLocal")
    val premiumInLocal: String,
    
    @field:JsonProperty("bidPremiumInLocal")
    val bidPremiumInLocal: String,
    
    @field:JsonProperty("askPremiumPercentage")
    val askPremiumPercentage: String,
    
    @field:JsonProperty("premiumPercentage")
    val premiumPercentage: String,
    
    @field:JsonProperty("bidPremiumPercentage")
    val bidPremiumPercentage: String,
    
    @field:JsonProperty("deltaShares")
    val deltaShares: String,
    
    @field:JsonProperty("deltaNotional")
    val deltaNotional: String,
    
    @field:JsonProperty("delta")
    val delta: String,
    
    @field:JsonProperty("deltaPercent")
    val deltaPercent: String,
    
    @field:JsonProperty("gammaShares")
    val gammaShares: String,
    
    @field:JsonProperty("gammaNotional")
    val gammaNotional: String,
    
    @field:JsonProperty("gamma")
    val gamma: String,
    
    @field:JsonProperty("gammaPercent")
    val gammaPercent: String,
    
    @field:JsonProperty("thetaShares")
    val thetaShares: String,
    
    @field:JsonProperty("thetaNotional")
    val thetaNotional: String,
    
    @field:JsonProperty("theta")
    val theta: String,
    
    @field:JsonProperty("thetaPercent")
    val thetaPercent: String,
    
    @field:JsonProperty("vegaShares")
    val vegaShares: String,
    
    @field:JsonProperty("vegaNotional")
    val vegaNotional: String,
    
    @field:JsonProperty("vega")
    val vega: String,
    
    @field:JsonProperty("vegaPercent")
    val vegaPercent: String,
    
    @field:JsonProperty("rhoShares")
    val rhoShares: String,
    
    @field:JsonProperty("rhoNotional")
    val rhoNotional: String,
    
    @field:JsonProperty("rho")
    val rho: String,
    
    @field:JsonProperty("rhoPercent")
    val rhoPercent: String,
    
    @field:JsonProperty("legs")
    val legs: List<RfqLeg>,
    
    @field:JsonProperty("assignedTo")
    val assignedTo: String? = null,
    
    @field:JsonProperty("priority")
    val priority: String? = null,
    
    @field:JsonProperty("lastActivity")
    val lastActivity: String? = null,
    
    @field:JsonProperty("createdBy")
    val createdBy: String? = null,
    
    @field:JsonProperty("createdAt")
    val createdAt: LocalDateTime = LocalDateTime.now(),
    
    @field:JsonProperty("updatedAt")
    val updatedAt: LocalDateTime = LocalDateTime.now()
)
