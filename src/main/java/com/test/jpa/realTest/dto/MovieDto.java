package com.test.jpa.realTest.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.test.jpa.realTest.entity.itemTable.ItemDtype;
import lombok.Data;

@Data
public class MovieDto extends ItemDto {

    private String director;

    private String actor;

    public MovieDto(){

    }

    public MovieDto(String director, String actor, String name, int price, int stockQuantity) {
        this.director = director;
        this.actor = actor;
        this.setName(name);
        this.setPrice(price);
        this.setStockQuantity(stockQuantity);
    }

    @QueryProjection
    public MovieDto(Long id, String director, String actor, String name, int price, int stockQuantity, ItemDtype itemDtype) {
        this.setId(id);
        this.director = director;
        this.actor = actor;
        this.setName(name);
        this.setPrice(price);
        this.setStockQuantity(stockQuantity);
        this.setItemDtype(itemDtype);
    }



}
