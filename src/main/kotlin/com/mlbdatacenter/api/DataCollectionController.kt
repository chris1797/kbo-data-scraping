package com.mlbdatacenter.api

import com.mlbdatacenter.service.KboDataScrapingService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/data")
class DataCollectionController(
    private val kboDataScrapingService: KboDataScrapingService
) {
    
    @PostMapping("/collect/pitcher/{season}")
    fun collectPitcherData(@PathVariable season: Int): ResponseEntity<Map<String, String>> {
        return try {
            kboDataScrapingService.scrapePitcherData(season)
            ResponseEntity.ok(mapOf("message" to "투수 데이터 수집 완료: $season"))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(mapOf("error" to "투수 데이터 수집 실패: ${e.message}"))
        }
    }
    
    @PostMapping("/collect/batter/{season}")
    fun collectBatterData(@PathVariable season: Int): ResponseEntity<Map<String, String>> {
        return try {
            kboDataScrapingService.scrapeBatterData(season)
            ResponseEntity.ok(mapOf("message" to "타자 데이터 수집 완료: $season"))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(mapOf("error" to "타자 데이터 수집 실패: ${e.message}"))
        }
    }
    
    @PostMapping("/collect/all/{season}")
    fun collectAllData(@PathVariable season: Int): ResponseEntity<Map<String, String>> {
        return try {
            kboDataScrapingService.scrapePitcherData(season)
            kboDataScrapingService.scrapeBatterData(season)
            ResponseEntity.ok(mapOf("message" to "전체 데이터 수집 완료: $season"))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(mapOf("error" to "전체 데이터 수집 실패: ${e.message}"))
        }
    }
    
    @PostMapping("/collect/recent")
    fun collectRecentData(): ResponseEntity<Map<String, String>> {
        return try {
            val currentYear = java.time.LocalDateTime.now().year
            
            // 최근 3년 데이터 수집
            for (year in currentYear - 2..currentYear) {
                kboDataScrapingService.scrapePitcherData(year)
                kboDataScrapingService.scrapeBatterData(year)
            }
            
            ResponseEntity.ok(mapOf("message" to "최근 3년 데이터 수집 완료"))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(mapOf("error" to "최근 데이터 수집 실패: ${e.message}"))
        }
    }
}
