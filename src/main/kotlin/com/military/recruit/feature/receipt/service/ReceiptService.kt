package com.military.recruit.feature.receipt.service

import com.military.recruit.common.extension.log
import com.military.recruit.config.env.OpenAPIEnv
import com.military.recruit.remote.ReceiptClient
import com.military.recruit.remote.dto.response.Receipt
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.CachePut
import org.springframework.stereotype.Service
import org.springframework.ui.Model

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
     * 공군 모집 조회
     *
     * @return List<Receipt> 모집 정보 리스트
     */
    @CachePut(value = ["AIR_FORCE"], key = "'AIR_FORCE_RECRUIT'")
    fun getAirForceRecruit(): List<Receipt> {
        log.info("[${this.javaClass.simpleName}::getAirForceRecruit()] 공군 지원 현황 가져오기 완료")
        val airForceReceipts: List<Receipt> = receiptClient.getReceipt(
            serviceKey = openAPIEnv.key,
            pageNo = 1,
            numOfRows = 10000
        ).body.items
            /** 866기 기간만 하드코딩으로 가져옴 -> 값 자동화 필요 */
            .filter { it.type == "공군" && it.startTime == "20241128" }
        return airForceReceipts
    }

    /**
     * 캐시 기반 모델 구성
     *
     * @param model
     */
    @Suppress("UNCHECKED_CAST")
    fun updateReceiptModel(model: Model) {
        /** cacheManager에서 정보 가져오기 - 없다면 method 호출 */
        var receipts: List<Receipt>? = cacheManager
            .getCache("AIR_FORCE")
            ?.get("AIR_FORCE_RECRUIT")
            ?.get() as? List<Receipt>

        if (receipts == null) {
            log.info("[${this.javaClass.simpleName}::updateReceiptModel()] cache 값이 비어 있어 값을 업데이트 합니다")
            receipts = getAirForceRecruit()
            cacheManager.getCache("AIR_FORCE")?.put("AIR_FORCE_RECRUIT", receipts)
        }

        /** 직군별 값 필터링 해서 요소 추가 */
        model.addAttribute("general", receipts.filter { it.category == "일반기술/전문기술병" }.toList())
        model.addAttribute("expert", receipts.filter { it.category != "일반기술/전문기술병" }.toList())
    }
}