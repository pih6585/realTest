package com.test.jpa.realTest.entity.itemTable;

import com.test.jpa.realTest.entity.CategoryItem;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Item {
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    @Column(name = "DTYPE")
    private String typeAlias;

   /* @OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
    private List<CategoryItem> categoryItems = new ArrayList<>();*/


}
