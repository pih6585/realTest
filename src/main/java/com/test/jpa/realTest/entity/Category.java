package com.test.jpa.realTest.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<CategoryItem> categoryItems = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    public Category createChild(Long id, String name){
        this.id = id;
        this.name = name;
        this.child.add(this);
        return this;
    }

    public Category createParent(Long id, Category parent, String name){
        this.id = id;
        this.name = name;
        this.parent = parent;
        this.child.add(this);
        return this;
    }

    public boolean existCategoryId(String name){
        boolean flag = false;
        List<Category> categories = child;
        for (Category category : categories) {
            if(category.getName().equals(name)){
                flag = true;
                break;
            }
        }
        return flag;
    }
}
