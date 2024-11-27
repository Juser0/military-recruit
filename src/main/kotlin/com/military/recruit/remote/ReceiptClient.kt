package com.military.recruit.remote

import com.military.recruit.remote.dto.response.ReceiptResponse
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.HttpExchange

@HttpExchange
interface ReceiptClient {

    @GetExchange("/list")
    fun getReceipt(
        @RequestParam serviceKey: String,
        @RequestParam pageNo: Int,
        @RequestParam numOfRows: Int): ReceiptResponse
}