package com.military.recruit.feature.receipt.service

import com.military.recruit.common.extension.log
import com.military.recruit.config.env.OpenAPIEnv
import com.military.recruit.feature.receipt.type.ReceiptType
import com.military.recruit.remote.ReceiptClient
import com.military.recruit.remote.dto.response.Receipt
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.CachePut
import org.springframework.stereotype.Service
import org.springframework.ui.Model
import java.time.LocalDateTime

/**
 * Receipt service
 * 실시간 지원현황 조회, 캐시 값 기반 모델 구성
 *
 * @author Juwon Lee
 */
@Service
class ReceiptService(
    private val cacheManager: CacheManager,
    private val receiptClient: ReceiptClient,
    private val openAPIEnv: OpenAPIEnv
) {

    /**
     * 전 군 모집 조회
     *
     * @return List<Receipt> 모집 정보 리스트
     */
    fun updateReceipts() {
        val receipts: List<Receipt> = getAllReceiptInfo()

        /** 군종별 지원 현황 캐싱  */
        ReceiptType.entries.forEach {
            log.info("[${this.javaClass.simpleName}::updateReceipts()] ${it.type} 지원 현황을 캐시에 저장합니다")
            val receipt: List<Receipt> = receipts.filter { filter -> filter.type == it.type && filter.startTime == "20241128" }
            cacheManager.getCache(it.cacheName)?.put(it.cacheName + "_RECRUIT", receipt)
        }

        /** 캐싱 시간 기록 */
        cacheManager.getCache("updatedAt")?.put("updatedAt", LocalDateTime.now())
    }

    private fun getAllReceiptInfo(): List<Receipt> {
        log.info("[${this.javaClass.simpleName}::getAllRecruitInfo()] 현재 모집병 지원 현황 가져오기 완료")
        return receiptClient.getReceipt(
            serviceKey = openAPIEnv.key,
            pageNo = 1,
            numOfRows = 10000
        ).body.items
    }

    /**
     * 캐시 기반 모델 구성
     *
     * @param model
     */
    @Suppress("UNCHECKED_CAST")
    fun updateReceiptModel(model: Model, type: ReceiptType) {

        /** cacheManager에서 정보 가져오기 */
        val receipts: List<Receipt>? = cacheManager
            .getCache(type.name)
            ?.get(type.name + "_RECRUIT")
            ?.get() as? List<Receipt>

        val updatedAt: LocalDateTime? = cacheManager
            .getCache("updatedAt")
            ?.get("updatedAt")
            ?.get() as? LocalDateTime

        /** 없다면 method 호출 */
        if (receipts == null) {
            log.info("[${this.javaClass.simpleName}::updateReceiptModel()] cache 값이 비어 있어 값을 업데이트 합니다")
        }

        /** 직군별 값 필터링 해서 요소 추가 */
        model.addAttribute("general", receipts?.filter { it.category == "일반기술/전문기술병" }?.toList())
        model.addAttribute("expert", receipts?.filter { it.category != "일반기술/전문기술병" }?.toList())

        model.addAttribute("updatedAt", updatedAt ?: "Unknown")
    }
}