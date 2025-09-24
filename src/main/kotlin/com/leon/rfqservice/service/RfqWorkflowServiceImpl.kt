package com.leon.rfqservice.service

import com.leon.rfqservice.model.RfqWorkflowEvent
import com.leon.rfqservice.repository.RfqWorkflowEventRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RfqWorkflowServiceImpl @Autowired constructor(private val workflowEventRepository: RfqWorkflowEventRepository) : RfqWorkflowService
{
    override fun addWorkflowEvent(event: RfqWorkflowEvent): RfqWorkflowEvent = workflowEventRepository.save(event)
    override fun getWorkflowEvents(rfqId: String): List<RfqWorkflowEvent> = workflowEventRepository.findByRfqIdOrderByTimestampDesc(rfqId)
}