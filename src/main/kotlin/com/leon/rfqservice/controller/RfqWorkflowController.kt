package com.leon.rfqservice.controller

import com.leon.rfqservice.model.RfqWorkflowEvent
import com.leon.rfqservice.model.enums.RfqStatus
import com.leon.rfqservice.model.enums.WorkflowAction
import com.leon.rfqservice.service.RfqWorkflowService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/rfq/workflow")
class RfqWorkflowController @Autowired constructor(private val workflowService: RfqWorkflowService)
{
    private val logger = LoggerFactory.getLogger(RfqWorkflowController::class.java)

    @PostMapping("/event")
    fun addWorkflowEvent(@RequestBody event: RfqWorkflowEvent): ResponseEntity<RfqWorkflowEvent> 
    {
        logger.info("Adding workflow event for RFQ: ${event.rfqId}")
        return try 
        {
            val addedEvent = workflowService.addWorkflowEvent(event)
            ResponseEntity.ok(addedEvent)
        } 
        catch (e: Exception) 
        {
            logger.error("Failed to add workflow event for RFQ: ${event.rfqId}", e)
            ResponseEntity.badRequest().build()
        }
    }

    @GetMapping("/events/{rfqId}")
    fun getWorkflowEvents(@PathVariable rfqId: String): ResponseEntity<List<RfqWorkflowEvent>> 
    {
        logger.info("Getting workflow events for RFQ: $rfqId")
        return try 
        {
            val events = workflowService.getWorkflowEvents(rfqId)
            ResponseEntity.ok(events)
        } 
        catch (e: Exception) 
        {
            logger.error("Failed to get workflow events for RFQ: $rfqId", e)
            ResponseEntity.badRequest().build()
        }
    }
}
