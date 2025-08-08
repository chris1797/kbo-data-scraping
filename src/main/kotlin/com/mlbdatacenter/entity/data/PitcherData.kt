package com.mlbdatacenter.entity.data

import java.math.BigDecimal

data class PitcherData(
    val playerId: String,
    val name: String,
    val team: String,
    val season: Int,
    val era: BigDecimal?,
    val games: Int?,
    val wins: Int?,
    val losses: Int?,
    val saves: Int?,
    val holds: Int?,
    val winPercentage: BigDecimal?,
    val inningsPitched: String?,
    val hits: Int?,
    val homeRuns: Int?,
    val walks: Int?,
    val hitByPitch: Int?,
    val strikeouts: Int?,
    val runs: Int?,
    val earnedRuns: Int?,
    val whip: BigDecimal?
)
