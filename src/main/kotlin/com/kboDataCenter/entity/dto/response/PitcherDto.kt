package com.kboDataCenter.entity.dto.response

import com.kboDataCenter.entity.PitcherStats
import com.kboDataCenter.entity.Player
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * DTO for {@link com.kboDataCenter.entity.PitcherStats}
 */
data class PitcherDto(
    val id: Long? = null,
    val season: Int? = null,
    val playerId: Long? = null,
    val player: Player? = null,
    val team: String? = null,
    val era: BigDecimal? = null,
    val games: Int? = null,
    val wins: Int? = null,
    val losses: Int? = null,
    val saves: Int? = null,
    val holds: Int? = null,
    val winPercentage: BigDecimal? = null,
    val inningsPitched: String? = null,
    val hits: Int? = null,
    val homeRuns: Int? = null,
    val walks: Int? = null,
    val hitByPitch: Int? = null,
    val strikeouts: Int? = null,
    val runs: Int? = null,
    val earnedRuns: Int? = null,
    val whip: BigDecimal? = null,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
) : Serializable {

    companion object {
        fun fromEntity(entity: PitcherStats): PitcherDto{
            return PitcherDto(
                id = entity.id,
                season = entity.season,
                team = entity.team,
                era = entity.era,
                games = entity.games,
                wins = entity.wins,
                losses = entity.losses,
                saves = entity.saves,
                holds = entity.holds,
                winPercentage = entity.winPercentage,
                inningsPitched = entity.inningsPitched.toString(),
                hits = entity.hits,
                homeRuns = entity.homeRuns,
                walks = entity.walks,
                hitByPitch = entity.hitByPitch,
                strikeouts = entity.strikeouts,
                runs = entity.runs,
                earnedRuns = entity.earnedRuns,
                whip = entity.whip,
            )

        }
    }
}