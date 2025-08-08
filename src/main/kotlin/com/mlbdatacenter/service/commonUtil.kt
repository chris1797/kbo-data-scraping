package com.mlbdatacenter.service

import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class commonUtil {

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