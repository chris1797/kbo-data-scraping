package com.kboDataCenter.api

import com.kboDataCenter.entity.dto.response.PitcherDto
import com.kboDataCenter.service.KboPitcherService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/kbo/pitcher")
class KboPitcherController(
    private val KboPitcherService: KboPitcherService
) {

    @GetMapping("/list")
    fun getPitcherList(pageable: Pageable): Page<PitcherDto> {
        return KboPitcherService.getPitcherList(pageable)
    }
}
