package com.test.jpa.realTest.repository;

import com.test.jpa.realTest.dto.CategoryDto;
import com.test.jpa.realTest.dto.OrderDto;
import com.test.jpa.realTest.dto.OrderItemDto;
import com.test.jpa.realTest.entity.Category;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void 카테고리_부모등록(){
        Category category1 = getCategory("양식");
        Category saveCategory = categoryRepository.save(category1);

        assertThat(category1.getName()).isEqualTo(saveCategory.getName());

    }

    @Test
    public void 카테고리_부모_자식등록(){
        Category category1 = getCategory("양식");
        Category saveCategory = categoryRepository.save(category1);

        Category category2 = Category.createParent("스테이크",category1);
        categoryRepository.save(category2);

        em.flush();
        em.clear();

        categoryRepository.findById(2L);

        assertThat(category2.getParent().getId()).isEqualTo(category1.getId());
        assertThat(category2.getParent().getName()).isEqualTo("양식");
        assertThat(category2.getName()).isEqualTo("스테이크");

    }

    @Test
    public void 카테고리_부모_조회(){
        Category category1 =  getCategory("양식");
        Category saveCategory1 = categoryRepository.save(category1);

        Category category2 = Category.createParent("스테이크",saveCategory1);
        categoryRepository.save(category2);

        Category category1_1 = getCategory("한식");
        categoryRepository.save(category1_1);

        em.flush();
        em.clear();

        List<CategoryDto> childList = categoryRepository.findByParentExistList();
        List<Long> parentIds = getParentIdList(childList);

        List<CategoryDto> resultList = categoryRepository.findByParentList(parentIds);

        assertThat(resultList).extracting("name").containsExactly("양식","한식");
    }

    @Test
    public void 카테고리_부모_자식_조회(){
        Category category1 =  getCategory("양식");
        Category saveCategory1 = categoryRepository.save(category1);

        Category category2 = Category.createParent("스테이크",saveCategory1);
        categoryRepository.save(category2);

        Category category1_1 = getCategory("한식");
        categoryRepository.save(category1_1);

        em.flush();
        em.clear();

        List<CategoryDto> childList = categoryRepository.findByParentExistList();
        List<Long> parentIds = getParentIdList(childList);

        List<CategoryDto> resultList = categoryRepository.findByParentList(parentIds);

        for (CategoryDto categoryDto : resultList) {
            categoryDto.setChild(getCategoryChildList(categoryDto.getId(),childList));
        }

        for (CategoryDto categoryDto : resultList) {
            List<CategoryDto> subList = categoryDto.getChild();
            for (CategoryDto dto : subList) {
                System.out.println(dto.getName());
            }
         }

        assertThat(resultList).extracting("name").containsExactly("양식","한식");
    }

    private List<Long> getParentIdList(List<CategoryDto> childList) {
        Map<Long, Long> parentMap = new HashMap<>();
        for (CategoryDto categoryDto : childList) {
            if (!parentMap.containsKey(categoryDto.getId())) {
                parentMap.put(categoryDto.getId(), categoryDto.getId());
            }
        }
        return new ArrayList<>(parentMap.keySet());
    }

    private List<CategoryDto> getCategoryChildList(Long id, List<CategoryDto> categoryChildList) {
        List<CategoryDto> categoryDtoList  = new ArrayList<>();
        for(CategoryDto categoryDto : categoryChildList){
            if(categoryDto.getParent_id().equals(id)){
                categoryDtoList.add(categoryDto);
            }
        }
        return categoryDtoList;
    };

    private Category getCategory( String name ) {
        return Category.createChild(name);
    }


}