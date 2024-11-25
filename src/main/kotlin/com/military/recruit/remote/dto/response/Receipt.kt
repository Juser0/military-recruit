package com.military.recruit.remote.dto.response

import jakarta.xml.bind.annotation.XmlElement
import jakarta.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = "item")
data class Receipt(
    @XmlElement(name = "rnum")
    val idx: String, // 데이터번호

    @XmlElement(name = "gunGbnm")
    val type: String, // 군구분코드명

    @XmlElement(name = "mojipGbnm")
    val category: String, // 모집구분명

    @XmlElement(name = "mojipYy")
    val year: String, // 모집년도

    @XmlElement(name = "mojipTms")
    val times: String, // 모집차수

    @XmlElement(name = "gsteukgiCd")
    val code: String, // 군사특기코드

    @XmlElement(name = "gsteukgiNm")
    val codeName: String, // 군사특기명

    @XmlElement(name = "iybudaeCdm")
    val enlistLocation: String, // 입영부대명

    @XmlElement(name = "seonbalPcnt")
    val selectCnt: String, // 선발인원수

    @XmlElement(name = "jeopsuPcnt")
    val registerCnt: String, // 접수인원수

    @XmlElement(name = "extremes")
    val extremes: String, // 접수-선발인원(과부족)

    @XmlElement(name = "rate")
    val rate: String, // 지원률

    @XmlElement(name = "jeopsuSjdtm")
    val startTime: String, // 접수시작일자

    @XmlElement(name = "jeopsuJrdtm")
    val endTime: String, // 접수종료일자

    @XmlElement(name = "iyyjsijakYm")
    val enlistDate: String, // 입영시작월

    @XmlElement(name = "iyyjjongryoYm")
    val dischargeDate: String, // 입영종료월

    @XmlElement(name = "ipyeongDe")
    val enlistValue: String // 입영년월값
)
