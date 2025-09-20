package com.leon.rfqservice.controller

import com.leon.rfqservice.model.Rfq
import com.leon.rfqservice.model.RfqWorkflowEvent
import com.leon.rfqservice.model.RfqComment
import com.leon.rfqservice.model.enums.RfqStatus
import com.leon.rfqservice.model.enums.WorkflowAction
import com.leon.rfqservice.service.RfqWorkflowService
import com.leon.rfqservice.service.RfqService
import com.leon.rfqservice.service.TraderInfo
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/rfq/workflow")
class RfqWorkflowController @Autowired constructor(
    private val workflowService: RfqWorkflowService,
    private val rfqService: RfqService
) 
{
    private val logger = LoggerFactory.getLogger(RfqWorkflowController::class.java)

    @PostMapping("/event")
    fun addWorkflowEvent(@RequestBody event: RfqWorkflowEvent): ResponseEntity<RfqWorkflowEvent> 
    {
        // TODO: Implement add workflow event endpoint
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
        // TODO: Implement get workflow events endpoint
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

    @PostMapping("/comment")
    fun addComment(@RequestBody comment: RfqComment): ResponseEntity<RfqComment> 
    {
        // TODO: Implement add comment endpoint
        logger.info("Adding comment for RFQ: ${comment.rfqId}")
        return try 
        {
            val addedComment = workflowService.addComment(comment)
            ResponseEntity.ok(addedComment)
        } 
        catch (e: Exception) 
        {
            logger.error("Failed to add comment for RFQ: ${comment.rfqId}", e)
            ResponseEntity.badRequest().build()
        }
    }

    @GetMapping("/comments/{rfqId}")
    fun getComments(@PathVariable rfqId: String): ResponseEntity<List<RfqComment>> 
    {
        // TODO: Implement get comments endpoint
        logger.info("Getting comments for RFQ: $rfqId")
        return try 
        {
            val comments = workflowService.getComments(rfqId)
            ResponseEntity.ok(comments)
        } 
        catch (e: Exception) 
        {
            logger.error("Failed to get comments for RFQ: $rfqId", e)
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/action/{rfqId}")
    fun processWorkflowAction(
        @PathVariable rfqId: String,
        @RequestParam action: String,
        @RequestParam userId: String,
        @RequestParam(required = false) comment: String?,
        @RequestBody(required = false) fieldChanges: Map<String, Any>?
    ): ResponseEntity<RfqWorkflowEvent> 
    {
        // TODO: Implement process workflow action endpoint
        logger.info("Processing workflow action: $action for RFQ: $rfqId")
        return try 
        {
            val actionEnum = WorkflowAction.valueOf(action.uppercase())
            val event = workflowService.processWorkflowAction(
                rfqId, 
                actionEnum, 
                userId, 
                comment, 
                fieldChanges ?: emptyMap()
            )
            ResponseEntity.ok(event)
        } 
        catch (e: Exception) 
        {
            logger.error("Failed to process workflow action: $action for RFQ: $rfqId", e)
            ResponseEntity.badRequest().build()
        }
    }

    @GetMapping("/transitions/{status}/{userRole}")
    fun getValidStatusTransitions(
        @PathVariable status: String,
        @PathVariable userRole: String
    ): ResponseEntity<List<RfqStatus>> 
    {
        // TODO: Implement get valid status transitions endpoint
        logger.info("Getting valid status transitions for status: $status, role: $userRole")
        return try 
        {
            val statusEnum = RfqStatus.valueOf(status.uppercase())
            val transitions = workflowService.getValidStatusTransitions(statusEnum, userRole)
            ResponseEntity.ok(transitions)
        } 
        catch (e: Exception) 
        {
            logger.error("Failed to get valid status transitions for status: $status, role: $userRole", e)
            ResponseEntity.badRequest().build()
        }
    }

    @GetMapping("/traders")
    fun getAvailableTraders(): ResponseEntity<List<TraderInfo>> 
    {
        // TODO: Implement get available traders endpoint
        logger.info("Getting available traders")
        return try 
        {
            val traders = workflowService.getAvailableTraders()
            ResponseEntity.ok(traders)
        } 
        catch (e: Exception) 
        {
            logger.error("Failed to get available traders", e)
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/status/{rfqId}")
    fun updateRfqStatus(
        @PathVariable rfqId: String,
        @RequestParam status: String,
        @RequestParam userId: String,
        @RequestParam(required = false) comment: String?
    ): ResponseEntity<Rfq> 
    {
        // TODO: Implement update RFQ status endpoint
        logger.info("Updating RFQ status: $rfqId to $status")
        return try 
        {
            val statusEnum = RfqStatus.valueOf(status.uppercase())
            val updatedRfq = rfqService.updateRfqStatus(rfqId, statusEnum, userId, comment)
            ResponseEntity.ok(updatedRfq)
        } 
        catch (e: Exception) 
        {
            logger.error("Failed to update RFQ status: $rfqId to $status", e)
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/assign/{rfqId}")
    fun assignRfq(
        @PathVariable rfqId: String,
        @RequestParam assignedTo: String,
        @RequestParam userId: String
    ): ResponseEntity<Rfq>
    {
        // TODO: Implement assign RFQ endpoint
        logger.info("Assigning RFQ: $rfqId to $assignedTo")
        return try 
        {
            val updatedRfq = rfqService.assignRfq(rfqId, assignedTo, userId)
            ResponseEntity.ok(updatedRfq)
        } 
        catch (e: Exception) 
        {
            logger.error("Failed to assign RFQ: $rfqId to $assignedTo", e)
            ResponseEntity.badRequest().build()
        }
    }
}
