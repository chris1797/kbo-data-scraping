package com.kboDataCenter.api

import com.kboDataCenter.entity.dto.response.PitcherDto
import com.kboDataCenter.service.KboPitcherService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/kbo/pitcher")
class KboPitcherController(
    private val kboPitcherService: KboPitcherService
) {

    /**
     * 투수 리스트 조회
     * @param pageable 페이징 정보
     * @return 투수 리스트
     */
    @GetMapping("/list")
    fun getPitcherList(pageable: Pageable): ResponseEntity<Page<PitcherDto>> {
        return ResponseEntity.ok(kboPitcherService.getPitcherList(pageable));
    }


}
