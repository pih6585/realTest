package com.test.jpa.realTest.entity.itemTable;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookTest {

    @Test
    public void 도서_등록() throws Exception{
        Book book = new Book();
        Book createBook = book.bookCreate("김영한", "JPA TEST", 1L, "JPA", 10000, 150);

        assertThat(createBook.getName()).isEqualTo("JPA");
        assertThat(createBook.getAuthor()).isEqualTo("김영한");
        assertThat(createBook.getPrice()).isEqualTo(10000);
        assertThat(createBook.getStockQuantity()).isEqualTo(150);
    }
}