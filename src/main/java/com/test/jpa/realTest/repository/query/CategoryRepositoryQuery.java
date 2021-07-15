package com.test.jpa.realTest.repository.query;

import com.test.jpa.realTest.dto.CategoryDto;

import java.util.List;

public interface CategoryRepositoryQuery {

    List<CategoryDto> findByParentExistList();

    List<CategoryDto> findByParentList(List<Long> parentIds);

}
