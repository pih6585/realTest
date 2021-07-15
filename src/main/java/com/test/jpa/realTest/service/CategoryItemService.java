package com.test.jpa.realTest.service;

import com.test.jpa.realTest.entity.Category;
import com.test.jpa.realTest.entity.CategoryItem;
import com.test.jpa.realTest.entity.itemTable.Item;
import com.test.jpa.realTest.repository.CategoryItemRepository;
import com.test.jpa.realTest.repository.CategoryRepository;
import com.test.jpa.realTest.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryItemService {

    @Autowired
    private CategoryItemRepository categoryItemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ItemRepository itemRepository;
    
    public void createCategoryItem(Long categoryId, Long itemId){
        Optional<Category> optCategory = categoryRepository.findById(categoryId);
        Category category = Optional.ofNullable(optCategory.get()).get();
        Optional<Item> optItem = itemRepository.findById(itemId);
        Item item = Optional.ofNullable(optItem.get()).get();
        CategoryItem categoryItem = CategoryItem.createCategoryItem(category,item);
        categoryItemRepository.save(categoryItem);
    }


}
