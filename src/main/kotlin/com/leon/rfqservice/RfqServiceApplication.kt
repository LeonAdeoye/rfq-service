package com.leon.rfqservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class RfqServiceApplication

fun main(args: Array<String>) 
{
    runApplication<RfqServiceApplication>(*args)
}
