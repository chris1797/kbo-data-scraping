package com.kboDataCenter.repository

import com.kboDataCenter.entity.PitcherStats
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PitcherStatsRepository : JpaRepository<PitcherStats, Long> {

    @EntityGraph(attributePaths = ["player"])
    @Query("SELECT p FROM PitcherStats p")
    fun findAllWithPlayer(pageable: Pageable): Page<PitcherStats>

    fun findByPlayerIdAndSeason(playerId: Long, season: Int): PitcherStats?
    fun findBySeason(season: Int): List<PitcherStats>
    fun findByTeamAndSeason(team: String, season: Int): List<PitcherStats>
    fun findBySeasonOrderByEraAsc(season: Int): List<PitcherStats>
    fun findBySeasonOrderByWhipAsc(season: Int): List<PitcherStats>
}
