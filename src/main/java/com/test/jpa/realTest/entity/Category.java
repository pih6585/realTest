package com.test.jpa.realTest.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Category {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "category_item",
     joinColumns = @JoinColumn(name = "category_id"),
     inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    protected Category() {
    }

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
