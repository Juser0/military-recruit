package com.military.recruit.feature.schedule.service

import com.military.recruit.common.extension.log
import com.military.recruit.feature.receipt.service.ReceiptService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.LocalDateTime

/**
 * Schedule Service
 * 주기적으로 실시간 지원 현황을 갱신하는 스케줄러
 *
 * @author Juwon Lee
 */
@Service
class ScheduleService(private val receiptService: ReceiptService) {

    /**
     * 지원 현황 갱신
     * 10초에 한 번 갱신 진행
     *
     */
    @Scheduled(cron = "0/10 * * * * *")
    fun updateReceiptInfo() {
        log.info("[START] 현재 지원 현황 업데이트 시작 | time = ${LocalDateTime.now()}")
        receiptService.getAirForceRecruit()
        log.info("[END] 현재 지원 현황 업데이트 완료 | time = ${LocalDateTime.now()}")
    }
}