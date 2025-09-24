package com.leon.rfqservice.service

import com.leon.rfqservice.model.Rfq

interface AmpsService 
{
    fun publishRfqUpdate(rfq: Rfq)
}
