package com.military.recruit.common.extension

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Logger 확장 함수
 * 클래스에 로거 선언한 것과 같이 동작
 *
 * @author Juwon Lee
 */
inline val <reified T> T.log: Logger
    get() {
        return if (T::class.isCompanion) {
            LoggerFactory.getLogger(T::class.java.enclosingClass)
        } else {
            LoggerFactory.getLogger(T::class.java)
        }
    }