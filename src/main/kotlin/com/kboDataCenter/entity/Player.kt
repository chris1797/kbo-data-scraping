package com.kboDataCenter.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "players")
class Player(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    
    @Column(unique = true, nullable = false)
    val playerId: String,
    
    @Column(nullable = false)
    val name: String,
    
    @Column(nullable = false)
    val team: String,
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val position: PlayerPosition,
    
    @Column
    val birthDate: String? = null,
    
    @Column
    val height: String? = null,
    
    @Column
    val weight: String? = null,
    
    @Column
    val throwHand: String? = null,
    
    @Column
    val batHand: String? = null,
    
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

enum class PlayerPosition {
    PITCHER, BATTER
}
