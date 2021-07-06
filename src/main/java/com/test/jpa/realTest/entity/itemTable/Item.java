package com.test.jpa.realTest.entity.itemTable;

import com.test.jpa.realTest.entity.CategoryItem;
import com.test.jpa.realTest.exception.NotEnoughStockException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE")
public abstract class Item  {
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "DTYPE",insertable = false, updatable = false)
    private ItemDtype itemDtype;

   @OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
    private List<CategoryItem> categoryItems = new ArrayList<>();

    public void orderCreateStock(int count){
       int reminderQty =  this.stockQuantity - count;
       if(reminderQty < 0){
        throw new NotEnoughStockException("재고 부족");
       }
        this.stockQuantity = reminderQty;
    };
}
