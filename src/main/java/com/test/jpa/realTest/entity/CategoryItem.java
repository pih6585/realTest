package com.test.jpa.realTest.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryItem {

    @Id
    @GeneratedValue
    @Column(name = "category_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;


    public static CategoryItem createCategoryItem(Category category, Item item) {
        CategoryItem categoryItem = new CategoryItem();
        categoryItem.setCategory(category);
        categoryItem.setItem(item);
        category.getCategoryItems().add(categoryItem);
        item.getCategoryItems().add(categoryItem);
        return categoryItem;
    }

}
