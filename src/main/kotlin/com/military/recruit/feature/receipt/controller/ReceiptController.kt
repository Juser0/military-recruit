package com.military.recruit.feature.receipt.controller

import com.military.recruit.feature.receipt.service.ReceiptService
import com.military.recruit.feature.receipt.type.ReceiptType
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
    fun getRokafInfo(model: Model): String {
        receiptService.updateReceiptModel(model, ReceiptType.AIR_FORCE)
        return "airForce"
    }

    @GetMapping("/roka")
    fun getRokaInfo(model: Model): String {
        receiptService.updateReceiptModel(model, ReceiptType.ARMY)
        return "army"
    }

    @GetMapping("/rokn")
    fun getRoknInfo(model: Model): String {
        receiptService.updateReceiptModel(model, ReceiptType.NAVY)
        return "navy"
    }

    @GetMapping("/rokmc")
    fun getRokmcInfo(model: Model): String {
        receiptService.updateReceiptModel(model, ReceiptType.MARINE_CORPS)
        return "marineCorps"
    }
}