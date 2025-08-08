package com.mlbdatacenter.repository

import com.mlbdatacenter.entity.Player
import com.mlbdatacenter.entity.PlayerPosition
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PlayerRepository : JpaRepository<Player, Long> {
    fun findByPlayerId(playerId: String): Player?
    fun findByPosition(position: PlayerPosition): List<Player>
    fun findByTeam(team: String): List<Player>
    fun findByNameContaining(name: String): List<Player>
}
