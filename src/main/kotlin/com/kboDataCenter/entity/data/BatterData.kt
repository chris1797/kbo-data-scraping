package com.kboDataCenter.entity.data

import java.math.BigDecimal

data class BatterData(
    val playerId: String,
    val name: String,
    val team: String,
    val season: Int,
    val battingAverage: BigDecimal?,
    val games: Int?,
    val plateAppearances: Int?,
    val atBats: Int?,
    val runs: Int?,
    val hits: Int?,
    val doubles: Int?,
    val triples: Int?,
    val homeRuns: Int?,
    val totalBases: Int?,
    val runsBattedIn: Int?,
    val sacrificeBunts: Int?,
    val sacrificeFlies: Int?
)
