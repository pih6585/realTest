package com.test.jpa.realTest.entity.itemTable;

import com.test.jpa.realTest.entity.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@DiscriminatorValue("Book")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Book extends Item {

    private String author;

    private String isbn;

    public static Book bookCreate(String author, String isbn, String name, int price, int stockQuantity) {
        Book book = new Book(author,isbn,name,price,stockQuantity);
        return book;
    }

    private Book(String author, String isbn, String name, int price, int stockQuantity){
        this.author = author;
        this.isbn = isbn;
        this.itemCreate(name, price, stockQuantity);
    }
}
