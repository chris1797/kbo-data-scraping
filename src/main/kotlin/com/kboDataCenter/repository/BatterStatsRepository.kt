package com.kboDataCenter.repository

import com.kboDataCenter.entity.BatterStats
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BatterStatsRepository : JpaRepository<BatterStats, Long> {
    fun findByPlayerIdAndSeason(playerId: Long, season: Int): BatterStats?
    fun findBySeason(season: Int): List<BatterStats>
    fun findByTeamAndSeason(team: String, season: Int): List<BatterStats>
    fun findBySeasonOrderByBattingAverageDesc(season: Int): List<BatterStats>
    fun findBySeasonOrderByHomeRunsDesc(season: Int): List<BatterStats>
}
