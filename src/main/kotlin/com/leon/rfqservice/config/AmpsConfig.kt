package com.leon.rfqservice.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class AmpsConfig 
{
    @Value("\${amps.server.url}")
    lateinit var serverUrl: String
    
    @Value("\${amps.client.name:RfqService}")
    lateinit var clientName: String
    
    @Value("\${amps.topic.inbound.gui}")
    lateinit var inboundGuiTopic: String
    
    @Value("\${amps.topic.inbound.exchange}")
    lateinit var inboundExchangeTopic: String
    
    @Value("\${amps.topic.outbound.gui}")
    lateinit var outboundGuiTopic: String
    
    @Value("\${amps.topic.outbound.exchange}")
    lateinit var outboundExchangeTopic: String
    
    @Value("\${amps.enabled:true}")
    var enabled: Boolean = true
}
