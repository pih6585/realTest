package com.test.jpa.realTest.repository.query;

import com.test.jpa.realTest.dto.MemberDto;

import java.util.List;

public interface MemberRepositoryQuery {

    MemberDto findByOneByDto(Long id);
    List<MemberDto> findByAllByDto();
}
