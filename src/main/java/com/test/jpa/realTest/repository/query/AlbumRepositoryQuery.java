package com.test.jpa.realTest.repository.query;

import com.test.jpa.realTest.dto.AlbumDto;

import java.util.List;

public interface AlbumRepositoryQuery {
    AlbumDto findByIdByDto(Long id);
    List<AlbumDto> findByAllByDto();
}
