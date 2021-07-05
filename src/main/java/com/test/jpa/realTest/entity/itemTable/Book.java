package com.test.jpa.realTest.entity.itemTable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.time.LocalDateTime;

@Entity
@Getter
@DiscriminatorValue("Book")
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
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
        this.setName(name);
        this.setPrice(price);
        this.setStockQuantity(stockQuantity);
    }
}
