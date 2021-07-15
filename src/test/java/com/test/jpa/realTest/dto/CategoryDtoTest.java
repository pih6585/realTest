package com.test.jpa.realTest.dto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class CategoryDtoTest {

    @Test
    public void 카테고리_dto_호출_테스트(){
        CategoryDto categoryDto1 = new CategoryDto("양식",null);

        assertThat(categoryDto1.getName()).isEqualTo("양식");

        CategoryDto categoryDto2 = new CategoryDto(1L,"한식","1",2L,"음식");

        assertThat(categoryDto2.getId()).isEqualTo(1L);
        assertThat(categoryDto2.getName()).isEqualTo("한식");
        assertThat(categoryDto2.getLv()).isEqualTo("1");
        assertThat(categoryDto2.getParent_id()).isEqualTo(2L);
        assertThat(categoryDto2.getParent_name()).isEqualTo("음식");

    }
}