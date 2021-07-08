package com.test.jpa.realTest.repository.query;

import com.test.jpa.realTest.dto.MemberDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberRepositoryQuery {

    MemberDto findByOneByDto(Long id);
    List<MemberDto> findByAllByDto();
    Page<MemberDto> findByAllByDtoPaging(Pageable pageable);
}
