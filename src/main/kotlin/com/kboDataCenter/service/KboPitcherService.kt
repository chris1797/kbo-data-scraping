package com.kboDataCenter.service

import com.kboDataCenter.entity.dto.response.PitcherDto
import com.kboDataCenter.repository.PitcherStatsRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class KboPitcherService(
    private val pitcherStatsRepository: PitcherStatsRepository
) {


    fun getPitcherList(pageable: Pageable): Page<PitcherDto> {
        return pitcherStatsRepository.findAllWithPlayer(pageable).map { pitcher ->
            PitcherDto(
                id = pitcher.id,
                player = pitcher.player,
                team = pitcher.team,
                era = pitcher.era,
                wins = pitcher.wins,
                losses = pitcher.losses,
                saves = pitcher.saves,
                strikeouts = pitcher.strikeouts,
                walks = pitcher.walks,
                hits = pitcher.hits,
                inningsPitched = pitcher.inningsPitched
            )
        }

    }
}
