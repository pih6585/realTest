package com.test.jpa.realTest.repository.query;

import com.test.jpa.realTest.dto.BookDto;

import java.util.List;

public interface BookRepositoryQuery {
    BookDto findByOneByDto(Long id);
    List<BookDto> findByAllByDto();
}
