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

    private Category(String name, Category parent){
        this.name = name;
        if(parent != null){
            this.parent = parent;
            this.parent.getChild().add(this);
        }
        this.child.add(this);
    }

    private Category(String name){
        this.name = name;
        this.child.add(this);
    }

    public static Category createChild(String name){
       Category category = new Category( name);
        return category;
    }

    public static Category createParent(String name, Category parent){
        Category category = new Category( name, parent);
        return category;
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
