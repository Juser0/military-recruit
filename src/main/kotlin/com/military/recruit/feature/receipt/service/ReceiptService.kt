package com.military.recruit.feature.receipt.service

import com.military.recruit.common.extension.log
import com.military.recruit.config.env.OpenAPIEnv
import com.military.recruit.remote.ReceiptClient
import com.military.recruit.remote.dto.response.Receipt
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.CachePut
import org.springframework.stereotype.Service
import org.springframework.ui.Model
import java.time.LocalDateTime

@Service
class ReceiptService(
    private val cacheManager: CacheManager,
    private val receiptClient: ReceiptClient,
    private val openAPIEnv: OpenAPIEnv
) {

    @CachePut(value = ["AIR_FORCE"], key = "'AIR_FORCE_RECRUIT'")
    fun getAirForceRecruit(): List<Receipt> {
        log.info("[${this.javaClass.simpleName}::getAirForceRecruit()] 공군 지원 현황 가져오기 완료")
        val airForceReceipts: List<Receipt> = receiptClient.getReceipt(
            serviceKey = openAPIEnv.key,
            pageNo = 1,
            numOfRows = 10000
        ).body.items
            .filter { it.type == "공군" && it.startTime == "20241128" }
        return airForceReceipts
    }

    @Suppress("UNCHECKED_CAST")
    fun updateReceiptModel(model: Model) {
        var receipts: List<Receipt>? = cacheManager
            .getCache("AIR_FORCE")
            ?.get("AIR_FORCE_RECRUIT")
            ?.get() as? List<Receipt>

        if (receipts == null) {
            log.info("[${this.javaClass.simpleName}::updateReceiptModel()] cache 값이 비어 있어 값을 업데이트 합니다")
            receipts = getAirForceRecruit()
            cacheManager.getCache("AIR_FORCE")?.put("AIR_FORCE_RECRUIT", receipts)
        }
        model.addAttribute("general", receipts.filter { it.category == "일반기술/전문기술병" }.toList())
        model.addAttribute("expert", receipts.filter { it.category != "일반기술/전문기술병" }.toList())
    }
}