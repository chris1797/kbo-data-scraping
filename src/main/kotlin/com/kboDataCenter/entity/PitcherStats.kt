package com.kboDataCenter.entity

import com.kboDataCenter.entity.dto.response.PitcherDto
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "pitcher_stats")
class PitcherStats(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id", nullable = false)
    val player: Player,
    
    @Column(nullable = false)
    val season: Int,
    
    @Column(nullable = false)
    val team: String,
    
    @Column(precision = 5, scale = 2)
    val era: BigDecimal? = null,
    
    @Column
    val games: Int? = null,
    
    @Column
    val wins: Int? = null,
    
    @Column
    val losses: Int? = null,
    
    @Column
    val saves: Int? = null,
    
    @Column
    val holds: Int? = null,
    
    @Column(precision = 5, scale = 3)
    val winPercentage: BigDecimal? = null,
    
    @Column
    val inningsPitched: String? = null,
    
    @Column
    val hits: Int? = null,
    
    @Column
    val homeRuns: Int? = null,
    
    @Column
    val walks: Int? = null,
    
    @Column
    val hitByPitch: Int? = null,
    
    @Column
    val strikeouts: Int? = null,
    
    @Column
    val runs: Int? = null,
    
    @Column
    val earnedRuns: Int? = null,
    
    @Column(precision = 4, scale = 2)
    val whip: BigDecimal? = null,
    
    @Column(nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),
    
    @Column(nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
) {
    @PreUpdate
    fun preUpdate() {
        updatedAt = LocalDateTime.now()
    }

    companion object {
        fun toDto(pitcherStats: PitcherStats): PitcherDto {
            return PitcherDto(
                id = pitcherStats.id,
                player = pitcherStats.player,
                team = pitcherStats.team,
                era = pitcherStats.era,
                wins = pitcherStats.wins,
                losses = pitcherStats.losses,
                saves = pitcherStats.saves,
                strikeouts = pitcherStats.strikeouts,
                walks = pitcherStats.walks,
                hits = pitcherStats.hits,
                inningsPitched = pitcherStats.inningsPitched
            )
        }
    }
}
