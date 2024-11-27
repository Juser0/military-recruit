package com.military.recruit.remote.dto.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "item")
data class Receipt(
    @JacksonXmlProperty(localName = "rnum")
    val idx: String, // 데이터번호

    @JacksonXmlProperty(localName = "gunGbnm")
    val type: String, // 군구분코드명

    @JacksonXmlProperty(localName = "mojipGbnm")
    val category: String, // 모집구분명

    @JacksonXmlProperty(localName = "mojipYy")
    val year: String, // 모집년도

    @JacksonXmlProperty(localName = "mojipTms")
    val times: String, // 모집차수

    @JacksonXmlProperty(localName = "gsteukgiCd")
    val code: String, // 군사특기코드

    @JacksonXmlProperty(localName = "gsteukgiNm")
    val codeName: String, // 군사특기명

    @JacksonXmlProperty(localName = "iybudaeCdm")
    val enlistLocation: String, // 입영부대명

    @JacksonXmlProperty(localName = "seonbalPcnt")
    val selectCnt: String, // 선발인원수

    @JacksonXmlProperty(localName = "jeopsuPcnt")
    val registerCnt: String, // 접수인원수

    @JacksonXmlProperty(localName = "extremes")
    val extremes: String, // 접수-선발인원(과부족)

    @JacksonXmlProperty(localName = "rate")
    val rate: String, // 지원률

    @JacksonXmlProperty(localName = "jeopsuSjdtm")
    val startTime: String, // 접수시작일자

    @JacksonXmlProperty(localName = "jeopsuJrdtm")
    val endTime: String, // 접수종료일자

    @JacksonXmlProperty(localName = "iyyjsijakYm")
    val enlistDate: String, // 입영시작월

    @JacksonXmlProperty(localName = "iyyjjongryoYm")
    val dischargeDate: String, // 입영종료월

    @JacksonXmlProperty(localName = "ipyeongDe")
    val enlistValue: String? // 입영년월값
)
