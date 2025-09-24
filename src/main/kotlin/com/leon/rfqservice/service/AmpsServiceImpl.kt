package com.leon.rfqservice.service

import com.leon.rfqservice.model.Rfq
import com.leon.rfqservice.model.RfqWorkflowEvent
import com.fasterxml.jackson.databind.ObjectMapper
import com.crankuptheamps.client.Client
import jakarta.annotation.PreDestroy
import jakarta.annotation.PostConstruct
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AmpsServiceImpl @Autowired constructor(
    private val objectMapper: ObjectMapper
) : AmpsService 
{
    private val logger = LoggerFactory.getLogger(AmpsServiceImpl::class.java)
    
    @Value("\${amps.server.url}")
    private lateinit var serverUrl: String
    
    @Value("\${amps.topic.outbound.gui}")
    private lateinit var outboundGuiTopic: String
    
    @Value("\${amps.topic.outbound.exchange}")
    private lateinit var outboundExchangeTopic: String
    
    @Value("\${amps.topic.inbound.gui}")
    private lateinit var inboundGuiTopic: String
    
    @Value("\${amps.topic.inbound.exchange}")
    private lateinit var inboundExchangeTopic: String
    
    @Value("\${amps.client.name:RfqService}")
    private lateinit var clientName: String
    
    @Value("\${amps.enabled:true}")
    private var ampsEnabled: Boolean = true
    
    private var isConnected = false
    private var ampsClient: Client? = null

    @PostConstruct
    fun initialize() 
    {
        if (!ampsEnabled)
        {
            logger.info("AMPS publishing is disabled via configuration")
            return
        }
        
        try 
        {
            logger.info("Initializing AMPS connection to $serverUrl")
            ampsClient = Client(clientName)
            ampsClient?.connect(serverUrl)
            ampsClient?.logon()
            isConnected = true
            logger.info("Successfully connected to AMPS server at $serverUrl")
        } 
        catch (e: Exception) 
        {
            logger.warn("Failed to connect to AMPS server at $serverUrl. AMPS publishing will be disabled. Error: ${e.message}")
            isConnected = false
            ampsClient = null
        }
    }

    override fun publishRfqUpdate(rfq: Rfq) 
    {
        if (!ampsEnabled)
        {
            logger.debug("AMPS publishing is disabled, skipping RFQ update publish for ${rfq.rfqId}")
            return
        }
        
        if (!isConnected || ampsClient == null) 
        {
            logger.debug("AMPS not connected, attempting to reconnect")
            try
            {
                initialize()
            }
            catch (e: Exception)
            {
                logger.debug("Failed to reconnect to AMPS, skipping RFQ update publish for ${rfq.rfqId}")
                return
            }
        }
        
        try
        {
            val jsonPayload = objectMapper.writeValueAsString(rfq)
            ampsClient?.publish(outboundGuiTopic, jsonPayload)
            logger.info("Published RFQ update for ${rfq.rfqId} to topic $outboundGuiTopic")
        }
        catch (e: Exception)
        {
            logger.error("Failed to publish RFQ update for ${rfq.rfqId}", e)
            isConnected = false
            ampsClient = null
        }
    }

    override fun publishWorkflowEvent(event: RfqWorkflowEvent) 
    {
        if (!ampsEnabled)
        {
            logger.debug("AMPS publishing is disabled, skipping workflow event publish for ${event.rfqId}")
            return
        }
        
        if (!isConnected || ampsClient == null) 
        {
            logger.debug("AMPS not connected, attempting to reconnect")
            try
            {
                initialize()
            }
            catch (e: Exception)
            {
                logger.debug("Failed to reconnect to AMPS, skipping workflow event publish for ${event.rfqId}")
                return
            }
        }
        
        try
        {
            val jsonPayload = objectMapper.writeValueAsString(event)
            ampsClient?.publish(outboundGuiTopic, jsonPayload)
            logger.info("Published workflow event for ${event.rfqId} to topic $outboundGuiTopic")
        }
        catch (e: Exception)
        {
            logger.error("Failed to publish workflow event for ${event.rfqId}", e)
            isConnected = false
            ampsClient = null
        }
    }

    override fun subscribeToRfqUpdates() 
    {
        // TODO: Implement subscribe to RFQ updates logic
        logger.info("Subscribing to RFQ updates from topic: $inboundGuiTopic")
    }

    override fun subscribeToWorkflowEvents() 
    {
        // TODO: Implement subscribe to workflow events logic
        logger.info("Subscribing to workflow events from topic: $inboundGuiTopic")
    }
    
    @PreDestroy
    fun shutdown() 
    {
        try 
        {
            if (ampsClient != null)
            {
                ampsClient?.disconnect()
                logger.info("Disconnected from AMPS server")
            }
        } 
        catch (e: Exception) 
        {
            logger.error("Error disconnecting from AMPS server", e)
        }
    }
}
