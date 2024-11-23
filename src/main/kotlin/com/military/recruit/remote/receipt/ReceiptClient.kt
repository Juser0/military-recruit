package com.military.recruit.remote.receipt

import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.service.annotation.GetExchange

@Component
interface ReceiptClient {

    @GetExchange("/endpoint")
    fun getReceipt(@RequestParam size: Int): String
}