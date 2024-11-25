package com.military.recruit.remote

import com.military.recruit.remote.dto.response.ReceiptResponse
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.service.annotation.GetExchange

@Component
interface ReceiptClient {

    @GetExchange("/endpoint")
    fun getReceipt(
        @RequestParam serviceKey: String,
        @RequestParam pageNo: Int,
        @RequestParam numOfRows: Int): ReceiptResponse
}