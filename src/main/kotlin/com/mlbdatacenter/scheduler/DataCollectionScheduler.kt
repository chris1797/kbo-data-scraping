package com.mlbdatacenter.scheduler

import com.mlbdatacenter.service.KboDataScrapingService
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class DataCollectionScheduler(
    private val kboDataScrapingService: KboDataScrapingService
) {
    private val logger = LoggerFactory.getLogger(this::class.java)
    
    // 매일 새벽 2시에 실행
    @Scheduled(cron = "0 0 2 * * ?")
    fun collectDailyData() {
        logger.info("일일 데이터 수집 시작: ${LocalDateTime.now()}")
        
        val currentYear = LocalDateTime.now().year
        
        try {
            // 최근 3년 데이터 수집
            for (year in currentYear - 2..currentYear) {
                kboDataScrapingService.scrapePitcherData(year)
                kboDataScrapingService.scrapeBatterData(year)
            }
            
            logger.info("일일 데이터 수집 완료: ${LocalDateTime.now()}")
        } catch (e: Exception) {
            logger.error("일일 데이터 수집 실패", e)
        }
    }
    
    // 매주 일요일 새벽 3시에 전체 데이터 재수집
    @Scheduled(cron = "0 0 3 ? * SUN")
    fun collectWeeklyFullData() {
        logger.info("주간 전체 데이터 수집 시작: ${LocalDateTime.now()}")
        
        val currentYear = LocalDateTime.now().year
        
        try {
            // 최근 5년 데이터 전체 재수집
            for (year in currentYear - 4..currentYear) {
                kboDataScrapingService.scrapePitcherData(year)
                kboDataScrapingService.scrapeBatterData(year)
            }
            
            logger.info("주간 전체 데이터 수집 완료: ${LocalDateTime.now()}")
        } catch (e: Exception) {
            logger.error("주간 전체 데이터 수집 실패", e)
        }
    }
}
