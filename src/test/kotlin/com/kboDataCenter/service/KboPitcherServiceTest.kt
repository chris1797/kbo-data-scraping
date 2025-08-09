package com.kboDataCenter.service

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest

@SpringBootTest
class KboPitcherServiceTest {

    @Autowired
    private lateinit var kboPitcherService: KboPitcherService


    @kotlin.test.Test
    fun `test getPitcherList returns non-empty list`() {
        val pageSize = 10

        val pageable = PageRequest.of(0, pageSize)
        val pitcherList = kboPitcherService.getPitcherList(pageable)

        assertNotNull(pitcherList)
        assertEquals(pitcherList.content.size, pageSize, "페이지 크기가 일치해야 합니다.")
    }
}