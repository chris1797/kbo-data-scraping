package com.kboDataCenter.entity

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "batter_stats")
class BatterStats(
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
    
    @Column(precision = 4, scale = 3)
    val battingAverage: BigDecimal? = null,
    
    @Column
    val games: Int? = null,
    
    @Column
    val plateAppearances: Int? = null,
    
    @Column
    val atBats: Int? = null,
    
    @Column
    val runs: Int? = null,
    
    @Column
    val hits: Int? = null,
    
    @Column
    val doubles: Int? = null,
    
    @Column
    val triples: Int? = null,
    
    @Column
    val homeRuns: Int? = null,
    
    @Column
    val totalBases: Int? = null,
    
    @Column
    val runsBattedIn: Int? = null,
    
    @Column
    val sacrificeBunts: Int? = null,
    
    @Column
    val sacrificeFlies: Int? = null,
    
    @Column(nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),
    
    @Column(nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
) {
    @PreUpdate
    fun preUpdate() {
        updatedAt = LocalDateTime.now()
    }
}
