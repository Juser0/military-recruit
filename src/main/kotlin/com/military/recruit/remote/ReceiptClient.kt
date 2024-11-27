package com.military.recruit.remote

import com.military.recruit.remote.dto.response.ReceiptResponse
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.HttpExchange

/**
 * Receipt Client
 * 선언형 외부 API Call Interface
 *
 * @author Juwon Lee
 */
@HttpExchange
interface ReceiptClient {

    /**
     * 실시간 지원현황 가져오기
     *
     * @param serviceKey apiKey
     * @param pageNo 페이지 번호
     * @param numOfRows 페이지 크기
     * @return ReceiptResponse 응답 값
     */
    @GetExchange("/list")
    fun getReceipt(
        @RequestParam serviceKey: String,
        @RequestParam pageNo: Int,
        @RequestParam numOfRows: Int): ReceiptResponse
}