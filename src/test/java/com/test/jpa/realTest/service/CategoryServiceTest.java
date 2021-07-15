package com.test.jpa.realTest.service;

import com.test.jpa.realTest.dto.CategoryDto;
import com.test.jpa.realTest.entity.Category;
import com.test.jpa.realTest.repository.CategoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void 카테고리_저장(){
        CategoryDto categoryDto1 = new CategoryDto("양식",null);
        CategoryDto categoryDto2 = new CategoryDto("한식",null);
        Long findDto1Id = categoryService.category_create(categoryDto1,0L);
        categoryService.category_create(categoryDto2,0L);
        CategoryDto categoryDto3 = new CategoryDto("스테이크",findDto1Id);
        Long findDto3Id = categoryService.category_create(categoryDto3,findDto1Id);

        em.flush();
        em.clear();

        Optional<Category> optResult1 = categoryRepository.findById(findDto1Id);
        Category findResult1 = Optional.ofNullable(optResult1.get()).get();
        assertThat(findResult1.getName()).isEqualTo("양식");

        Optional<Category> optResult2 = categoryRepository.findById(findDto3Id);
        Category findResult2 = Optional.ofNullable(optResult2.get()).get();

        assertThat(findResult2.getName()).isEqualTo("스테이크");
        assertThat(findResult2.getParent().getName()).isEqualTo("양식");
    }

    @Test
    public void 부모_리스트_조회(){
        CategoryDto categoryDto1 = new CategoryDto("양식",null);
        CategoryDto categoryDto2 = new CategoryDto("한식",null);
        Long findDto1Id = categoryService.category_create(categoryDto1,0L);
        categoryService.category_create(categoryDto2,0L);
        CategoryDto categoryDto3 = new CategoryDto("스테이크",findDto1Id);
        categoryService.category_create(categoryDto3,findDto1Id);

        em.flush();
        em.clear();

        List<CategoryDto> resultList = categoryService.findByParentIdList();

        assertThat(resultList).extracting("name").containsExactly("양식","한식");

    }

    @Test
    public void 전체_리스트_조회(){
        CategoryDto categoryDto1 = new CategoryDto("양식",null);
        CategoryDto categoryDto2 = new CategoryDto("한식",null);
        Long findDto1Id = categoryService.category_create(categoryDto1,0L);
        categoryService.category_create(categoryDto2,0L);
        CategoryDto categoryDto3 = new CategoryDto("스테이크",findDto1Id);
        categoryService.category_create(categoryDto3,findDto1Id);

        em.flush();
        em.clear();

        List<CategoryDto> resultList = categoryService.findByAll();

        assertThat(resultList).extracting("name").containsExactly("양식","한식");

        for (CategoryDto categoryDto : resultList) {
            List<CategoryDto> child = categoryDto.getChild();
            for (CategoryDto dto : child) {
                assertThat(dto.getName()).isEqualTo("스테이크");
            }
        }
    }
}