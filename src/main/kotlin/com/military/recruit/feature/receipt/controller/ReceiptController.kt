package com.military.recruit.feature.receipt.controller

import com.military.recruit.remote.ReceiptClient
import org.springframework.web.bind.annotation.RestController

@RestController
class ReceiptController(private val receiptClient: ReceiptClient) {
    fun getInfo() {
    }
}