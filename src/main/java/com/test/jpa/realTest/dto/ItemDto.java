package com.test.jpa.realTest.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.test.jpa.realTest.entity.itemTable.ItemDtype;
import lombok.Data;


@Data
public class ItemDto {

    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    private ItemDtype itemDtype;

    private String itemDtypeName;

    public ItemDto(){

    }

    @QueryProjection
    public ItemDto(Long id, String name, int price, int stockQuantity, ItemDtype itemDtype, String itemDtypeName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.itemDtype = itemDtype;
        this.itemDtypeName = itemDtypeName;
    }
}
