package com.kboDataCenter.service

import com.kboDataCenter.entity.*
import com.kboDataCenter.entity.data.BatterData
import com.kboDataCenter.entity.data.PitcherData
import com.kboDataCenter.repository.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class KboDataScrapingService(
    private val playerRepository: PlayerRepository,
    private val kboDataScrapingExtract: KboDataScrapingExtract,
    private val pitcherStatsRepository: PitcherStatsRepository,
    private val batterStatsRepository: BatterStatsRepository,
) {
    private val logger = LoggerFactory.getLogger(this::class.java)
    
    companion object {
        private const val KBO_BASE_URL = "https://www.koreabaseball.com"
        private const val USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36"
    }
    
    @Transactional
    fun scrapePitcherData(season: Int) {
        logger.info("투수 데이터 스크래핑 시작: $season")
        
        try {
            val url = "$KBO_BASE_URL/Record/Player/PitcherBasic/Basic1.aspx"
            val doc = getDocument(url)
            
            val table = doc.select("table").firstOrNull()
            if (table != null) {
                val rows = table.select("tr")
                
                for (i in 1 until rows.size) { // 헤더 제외
                    val row = rows[i]
                    val cells = row.select("td")
                    
                    if (cells.size >= 18) {
                        val playerData = kboDataScrapingExtract.extractPitcherData(cells, season)
                        if (playerData != null) {
                            savePitcherData(playerData)
                        }
                    }
                }
            }
            
            logger.info("투수 데이터 스크래핑 완료: $season")
        } catch (e: Exception) {
            logger.error("투수 데이터 스크래핑 실패: $season", e)
        }
    }
    
    @Transactional
    fun scrapeBatterData(season: Int) {
        logger.info("타자 데이터 스크래핑 시작: $season")
        
        try {
            val url = "$KBO_BASE_URL/Record/Player/HitterBasic/Basic1.aspx"
            logger.info("타자 데이터 스크래핑 URL: $url")
            val doc = getDocument(url)
            
            val table = doc.select("table").firstOrNull()
            if (table != null) {
                val rows = table.select("tr")
                logger.info("타자 테이블 행 수: ${rows.size}")
                logger.info("테이블 HTML: ${table.html()}")

                for (i in 1 until rows.size) { // 헤더 제외
                    val row = rows[i]
                    val cells = row.select("td")

                    if (cells.size >= 16) { // KBO 타자 테이블은 16개 컬럼
                        val playerData = kboDataScrapingExtract.extractBatterData(cells, season)
                        if (playerData != null) {
                            saveBatterData(playerData)
                            logger.info("타자 데이터 저장: ${playerData.name}")
                        } else {
                            logger.warn("타자 데이터 추출 실패: 행 $i")
                        }
                    } else {
                        logger.warn("셀 수 부족: 행 $i, 셀 수: ${cells.size}")
                    }
                }
            } else {
                logger.error("타자 테이블을 찾을 수 없습니다")
            }

            logger.info("타자 데이터 스크래핑 완료: $season")
        } catch (e: Exception) {
            logger.error("타자 데이터 스크래핑 실패: $season", e)
        }
        
        return
    }
    
    private fun getDocument(url: String): Document {
        return Jsoup.connect(url)
            .userAgent(USER_AGENT)
            .timeout(10000)
            .get()
    }
    
    private fun savePitcherData(data: PitcherData) {
        val player = playerRepository.findByPlayerId(data.playerId) 
            ?: Player(
                playerId = data.playerId,
                name = data.name,
                team = data.team,
                position = PlayerPosition.PITCHER
            ).also { playerRepository.save(it) }
        
        val existingStats = pitcherStatsRepository.findByPlayerIdAndSeason(player.id!!, data.season)
        if (existingStats == null) {
            val stats = PitcherStats(
                player = player,
                season = data.season,
                team = data.team,
                era = data.era,
                games = data.games,
                wins = data.wins,
                losses = data.losses,
                saves = data.saves,
                holds = data.holds,
                winPercentage = data.winPercentage,
                inningsPitched = data.inningsPitched,
                hits = data.hits,
                homeRuns = data.homeRuns,
                walks = data.walks,
                hitByPitch = data.hitByPitch,
                strikeouts = data.strikeouts,
                runs = data.runs,
                earnedRuns = data.earnedRuns,
                whip = data.whip
            )
            pitcherStatsRepository.save(stats)
        }
    }
    
    private fun saveBatterData(data: BatterData) {
        val player = playerRepository.findByPlayerId(data.playerId)
            ?: Player(
                playerId = data.playerId,
                name = data.name,
                team = data.team,
                position = PlayerPosition.BATTER
            ).also { playerRepository.save(it) }
        
        val existingStats = batterStatsRepository.findByPlayerIdAndSeason(player.id!!, data.season)
        if (existingStats == null) {
            val stats = BatterStats(
                player = player,
                season = data.season,
                team = data.team,
                battingAverage = data.battingAverage,
                games = data.games,
                plateAppearances = data.plateAppearances,
                atBats = data.atBats,
                runs = data.runs,
                hits = data.hits,
                doubles = data.doubles,
                triples = data.triples,
                homeRuns = data.homeRuns,
                totalBases = data.totalBases,
                runsBattedIn = data.runsBattedIn,
                sacrificeBunts = data.sacrificeBunts,
                sacrificeFlies = data.sacrificeFlies
            )
            batterStatsRepository.save(stats)
            logger.info("새로운 타자 데이터 저장: ${data.name}")
        } else {
            logger.info("이미 존재하는 타자 데이터 스킵: ${data.name}")
        }
    }
}
