package com.test.jpa.realTest.service;

import com.test.jpa.realTest.dto.CategoryDto;
import com.test.jpa.realTest.entity.Category;
import com.test.jpa.realTest.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public Long category_create(CategoryDto categoryDto){
        Category parentCategory = getParentById(categoryDto);
        Category category = Category.categoryCreate(categoryDto.getName(),parentCategory);
        Category saveCategory = categoryRepository.save(category);
        return saveCategory.getId();
    }

    private Category getParentById(CategoryDto categoryDto) {
        Category parentCategory = null;
        if(categoryDto.getParent_id() != null){
            Optional<Category> optCategory = categoryRepository.findById(categoryDto.getParent_id());
            parentCategory = Optional.ofNullable(optCategory.get()).get();
        }
        return parentCategory;
    }

    public List<CategoryDto> findByAll(){
        List<CategoryDto> resultList = getCategoryParentList();
        for (CategoryDto categoryDto : resultList) {
            categoryDto.setChild(getCategoryChildList(categoryDto.getId(),getChildList()));
        }
        return resultList;
    }

    public List<CategoryDto> findByParentIdList(){
        List<CategoryDto> resultList = getCategoryParentList();
        return resultList;
    }

    private List<CategoryDto> getChildList(){
        List<CategoryDto> childList = categoryRepository.findByParentExistList();
        return childList;
    }

    private List<CategoryDto> getCategoryParentList() {
        List<Long> parentIds = getParentIdList(getChildList());

        List<CategoryDto> resultList = categoryRepository.findByParentList(parentIds);
        return resultList;
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
}
