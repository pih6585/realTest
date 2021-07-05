package com.test.jpa.realTest.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.test.jpa.realTest.entity.itemTable.ItemDtype;
import lombok.Data;

@Data
public class BookDto {

    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    private ItemDtype itemDtype;

    private String author;

    private String isbn;

    public BookDto(){

    }

    public BookDto(String author, String isbn, String name, int price, int stockQuantity) {
        this.author = author;
        this.isbn = isbn;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    @QueryProjection
    public BookDto(Long id, String author, String isbn, String name, int price, int stockQuantity, ItemDtype itemDtype) {
        this.id = id;
        this.author = author;
        this.isbn = isbn;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.itemDtype = itemDtype;
    }
}
