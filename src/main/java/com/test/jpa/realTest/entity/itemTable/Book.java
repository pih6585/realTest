package com.test.jpa.realTest.entity.itemTable;

import com.test.jpa.realTest.entity.Item;
import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@DiscriminatorValue("Book")
public class Book extends Item {

    private String author;

    private String isbn;

    public Book() {
    }

    public Book bookCreate(String author, String isbn, Long id, String name, int price, int stockQuantity) {
        this.author = author;
        this.isbn = isbn;
        this.itemCreate(id, name, price, stockQuantity);
        return this;
    }
}
