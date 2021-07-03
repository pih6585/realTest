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
       Item book =  Book.bookCreate("김영한","null","JPA",10000,100);

        assertThat(book.getName()).isEqualTo("JPA");
        assertThat(book.getPrice()).isEqualTo(10000);

        Item album = Album.albumCreate("아이유",null,"봄여름가을",10000,100);
        assertThat(album.getName()).isEqualTo("봄여름가을");
        assertThat(album.getPrice()).isEqualTo(10000);

        Item movie = Movie.movieCreate("류승룡","류승범","사생결단",9900,30);

        assertThat(movie.getName()).isEqualTo("사생결단");
        assertThat(movie.getPrice()).isEqualTo(9900);
    }
}