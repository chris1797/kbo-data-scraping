package com.kboDataCenter.service

import com.kboDataCenter.entity.dto.data.BatterData
import com.kboDataCenter.entity.dto.data.PitcherData
import org.jsoup.select.Elements
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component


@Component
class KboDataScrapingExtract(
    private val commonUtil: commonUtil
) {

    private val logger = LoggerFactory.getLogger(this::class.java)


    fun extractPitcherData(cells: Elements, season: Int): PitcherData? {
        return try {
            val nameCell = cells[1].select("a").firstOrNull()
            val playerId = nameCell?.attr("href")?.let { extractPlayerId(it) } ?: return null
            val name = nameCell.text().trim()
            val team = cells[2].text().trim()

            PitcherData(
                playerId = playerId,
                name = name,
                team = team,
                season = season,
                era = commonUtil.parseBigDecimal(cells[3].text()),
                games = commonUtil.parseInt(cells[4].text()),
                wins = commonUtil.parseInt(cells[5].text()),
                losses = commonUtil.parseInt(cells[6].text()),
                saves = commonUtil.parseInt(cells[7].text()),
                holds = commonUtil.parseInt(cells[8].text()),
                winPercentage = commonUtil.parseBigDecimal(cells[9].text()),
                inningsPitched = cells[10].text().trim(),
                hits = commonUtil.parseInt(cells[11].text()),
                homeRuns = commonUtil.parseInt(cells[12].text()),
                walks = commonUtil.parseInt(cells[13].text()),
                hitByPitch = commonUtil.parseInt(cells[14].text()),
                strikeouts = commonUtil.parseInt(cells[15].text()),
                runs = commonUtil.parseInt(cells[16].text()),
                earnedRuns = commonUtil.parseInt(cells[17].text()),
                whip = commonUtil.parseBigDecimal(cells[18].text())
            )
        } catch (e: Exception) {
            logger.error("투수 데이터 추출 실패", e)
            null
        }

    }

    fun extractBatterData(cells: Elements, season: Int): BatterData? {
        return try {
            val nameCell = cells[1].select("a").firstOrNull()
            val playerId = nameCell?.attr("href")?.let { extractPlayerId(it) } ?: return null
            val name = nameCell.text().trim()
            val team = cells[2].text().trim()

            BatterData(
                playerId = playerId,
                name = name,
                team = team,
                season = season,
                battingAverage = commonUtil.parseBigDecimal(cells[3].text()),
                games = commonUtil.parseInt(cells[4].text()),
                plateAppearances = commonUtil.parseInt(cells[5].text()),
                atBats = commonUtil.parseInt(cells[6].text()),
                runs = commonUtil.parseInt(cells[7].text()),
                hits = commonUtil.parseInt(cells[8].text()),
                doubles = commonUtil.parseInt(cells[9].text()),
                triples = commonUtil.parseInt(cells[10].text()),
                homeRuns = commonUtil.parseInt(cells[11].text()),
                totalBases = commonUtil.parseInt(cells[12].text()),
                runsBattedIn = commonUtil.parseInt(cells[13].text()),
                sacrificeBunts = commonUtil.parseInt(cells[14].text()),
                sacrificeFlies = commonUtil.parseInt(cells[15].text())
            )
        } catch (e: Exception) {
            logger.error("타자 데이터 추출 실패", e)
            null
        }
    }

    private fun extractPlayerId(href: String): String? {
        val regex = "playerId=([^&]+)".toRegex()
        return regex.find(href)?.groupValues?.get(1)
    }
}