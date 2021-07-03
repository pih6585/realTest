package com.test.jpa.realTest.entity;

import com.test.jpa.realTest.entity.itemTable.Album;
import com.test.jpa.realTest.entity.itemTable.Book;
import com.test.jpa.realTest.entity.itemTable.Movie;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemTest {

    @Test
    public void 제품_등록(){
       // Item item1 = new Book();
       //Item book = item1.itemCreate(1L, "JPA", 10000, 1000);

       // assertThat(book.getName()).isEqualTo("JPA");
        //assertThat(book.getPrice()).isEqualTo(10000);

        Item album = Album.albumCreate("김영한","JPA TEST","JPA",10000,100);
        assertThat(album.getName()).isEqualTo("아이유");
        assertThat(album.getPrice()).isEqualTo(5000);

        //Item item3 = new Movie();
       // Item movie = item3.itemCreate(1L, "킬빌V1", 5000, 1000);

        //assertThat(movie.getName()).isEqualTo("킬빌V1");
        //assertThat(movie.getPrice()).isEqualTo(5000);
    }
}