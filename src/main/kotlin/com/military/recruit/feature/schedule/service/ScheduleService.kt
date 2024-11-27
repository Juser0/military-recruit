package com.military.recruit.feature.schedule.service

import com.military.recruit.common.extension.log
import com.military.recruit.feature.receipt.service.ReceiptService
import jakarta.annotation.PostConstruct
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ScheduleService(private val receiptService: ReceiptService) {

    @Scheduled(cron = "0/30 * * * * *")
    fun updateReceiptInfo() {
        log.info("[START] 현재 지원 현황 업데이트 시작 | time = ${LocalDateTime.now()}")
        receiptService.getAirForceRecruit()
        log.info("[END] 현재 지원 현황 업데이트 완료 | time = ${LocalDateTime.now()}")
    }
}