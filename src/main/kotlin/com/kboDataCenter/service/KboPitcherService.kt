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
        return pitcherStatsRepository.findAllWithPlayer(pageable)
            .map { pitcher -> PitcherDto.fromEntity(pitcher) }
    }
}
