package com.leon.rfqservice.service.impl

import com.leon.rfqservice.model.RfqWorkflowEvent
import com.leon.rfqservice.model.RfqComment
import com.leon.rfqservice.model.enums.RfqStatus
import com.leon.rfqservice.model.enums.WorkflowAction
import com.leon.rfqservice.repository.RfqWorkflowEventRepository
import com.leon.rfqservice.repository.RfqCommentRepository
import com.leon.rfqservice.service.RfqWorkflowService
import com.leon.rfqservice.service.TraderInfo
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.UUID

@Service
class RfqWorkflowServiceImpl @Autowired constructor(private val workflowEventRepository: RfqWorkflowEventRepository, private val commentRepository: RfqCommentRepository) : RfqWorkflowService
{
    private val logger = LoggerFactory.getLogger(RfqWorkflowServiceImpl::class.java)

    override fun addWorkflowEvent(event: RfqWorkflowEvent): RfqWorkflowEvent 
    {
        // TODO: Implement add workflow event logic
        logger.info("Adding workflow event for RFQ: ${event.rfqId}")
        return workflowEventRepository.save(event)
    }

    override fun getWorkflowEvents(rfqId: String): List<RfqWorkflowEvent> 
    {
        // TODO: Implement get workflow events logic
        logger.info("Getting workflow events for RFQ: $rfqId")
        return workflowEventRepository.findByRfqIdOrderByTimestampDesc(rfqId)
    }

    override fun addComment(comment: RfqComment): RfqComment 
    {
        // TODO: Implement add comment logic
        logger.info("Adding comment for RFQ: ${comment.rfqId}")
        return commentRepository.save(comment)
    }

    override fun getComments(rfqId: String): List<RfqComment> 
    {
        // TODO: Implement get comments logic
        logger.info("Getting comments for RFQ: $rfqId")
        return commentRepository.findByRfqIdOrderByTimestampDesc(rfqId)
    }

    override fun processWorkflowAction(rfqId: String, action: WorkflowAction, userId: String, comment: String?, fieldChanges: Map<String, Any>): RfqWorkflowEvent 
    {
        // TODO: Implement process workflow action logic
        logger.info("Processing workflow action: $action for RFQ: $rfqId")
        
        val event = RfqWorkflowEvent(
            id = UUID.randomUUID().toString(),
            rfqId = rfqId,
            eventType = action,
            fromStatus = null, // TODO: Get current status
            toStatus = null, // TODO: Determine new status based on action
            userId = userId,
            timestamp = LocalDateTime.now(),
            comment = comment,
            fieldChanges = fieldChanges.mapValues { (key, value) ->
                com.leon.rfqservice.model.FieldChange(
                    fieldName = key,
                    oldValue = null, // TODO: Get old value
                    newValue = value,
                    changeType = "UPDATED"
                )
            }
        )
        
        return addWorkflowEvent(event)
    }

    override fun getValidStatusTransitions(currentStatus: RfqStatus, userRole: String): List<RfqStatus> 
    {
        // TODO: Implement get valid status transitions logic
        logger.info("Getting valid status transitions for status: $currentStatus, role: $userRole")
        
        return when (currentStatus) 
        {
            RfqStatus.PENDING -> 
            {
                if (userRole == "ST") 
                    listOf(RfqStatus.ACCEPTED, RfqStatus.REJECTED)
                else 
                    emptyList()
            }
            RfqStatus.ACCEPTED -> 
            {
                if (userRole == "ST") 
                    listOf(RfqStatus.PRICING)
                else 
                    emptyList()
            }
            RfqStatus.PRICING -> 
            {
                if (userRole == "RT") 
                    listOf(RfqStatus.PRICED)
                else 
                    emptyList()
            }
            RfqStatus.PRICED -> 
            {
                if (userRole == "ST") 
                    listOf(RfqStatus.TRADED_AWAY, RfqStatus.TRADE_COMPLETED)
                else 
                    emptyList()
            }
            else -> emptyList()
        }
    }

    override fun getAvailableTraders(): List<TraderInfo> 
    {
        // TODO: Implement get available traders logic
        logger.info("Getting available traders")
        
        return listOf(
            TraderInfo("john-smith", "John Smith (RT)", "RT"),
            TraderInfo("jane-doe", "Jane Doe (RT)", "RT"),
            TraderInfo("mike-wilson", "Mike Wilson (RT)", "RT"),
            TraderInfo("sarah-johnson", "Sarah Johnson (ST)", "ST"),
            TraderInfo("alex-brown", "Alex Brown (ST)", "ST")
        )
    }
}
