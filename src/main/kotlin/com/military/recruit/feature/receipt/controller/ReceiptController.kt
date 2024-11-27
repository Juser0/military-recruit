package com.military.recruit.feature.receipt.controller

import com.military.recruit.feature.receipt.service.ReceiptService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

/**
 * Receipt controller
 * 모집 현황 View Controller
 * - 현재는 공군만 지원
 *
 * @author Juwon Lee
 */
@Controller
class ReceiptController(
    private val receiptService: ReceiptService
) {
    @GetMapping("/rokaf")
    fun getInfo(model: Model): String {
        receiptService.updateReceiptModel(model)
        return "airForce"
    }
}