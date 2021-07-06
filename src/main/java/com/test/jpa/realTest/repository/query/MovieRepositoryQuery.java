package com.test.jpa.realTest.repository.query;

import com.test.jpa.realTest.dto.MovieDto;

import java.util.List;

public interface MovieRepositoryQuery {
    MovieDto findByOneByDto(Long id);
    List<MovieDto> findByAllByDto();

}
