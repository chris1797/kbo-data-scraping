package com.kboDataCenter.service

import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class commonUtil {

    /**
     * 문자열을 정수로 변환
     */
    fun parseInt(value: String): Int? {
        return try {
            value.trim().replace("-", "0").toInt()
        } catch (e: Exception) {
            null
        }
    }

    fun parseBigDecimal(value: String): BigDecimal? {
        return try {
            BigDecimal(value.trim().replace("-", "0"))
        } catch (e: Exception) {
            null
        }
    }
}