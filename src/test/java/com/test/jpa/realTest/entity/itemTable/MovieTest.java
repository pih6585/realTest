package com.test.jpa.realTest.entity.itemTable;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MovieTest {

    @Test
    public void 영화_등록() throws Exception{
        Movie movie = new Movie();
        Movie createMovie = movie.movieCreate("대영팬더", "JPA TEST", 1L, "킬빌", 10000, 150);

        assertThat(createMovie.getName()).isEqualTo("킬빌");
        assertThat(createMovie.getDirector()).isEqualTo("대영팬더");
        assertThat(createMovie.getPrice()).isEqualTo(10000);
        assertThat(createMovie.getStockQuantity()).isEqualTo(150);
    }
}