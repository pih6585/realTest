package com.test.jpa.realTest.service;

import com.test.jpa.realTest.dto.BookDto;
import com.test.jpa.realTest.dto.MovieDto;
import com.test.jpa.realTest.entity.itemTable.Movie;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MovieServiceTest {

    @Autowired MovieService movieService;

    @Autowired
    EntityManager em;
    
    @Test
    public void 영화_저장() throws Exception{
        MovieDto movieDto1 = new MovieDto("마블","울버린","엑스맨",10000,250);
        MovieDto movieDto2 = new MovieDto("류승룡","류승범","사생결단",9900,200);

        Long saveById1 = movieService.movieCreate(movieDto1);
        Long saveById2 = movieService.movieCreate(movieDto2);

        MovieDto findMovie1 = movieService.movieFindOne(saveById1);
        MovieDto findMovie2 = movieService.movieFindOne(saveById2);

        assertThat(findMovie1.getActor()).isEqualTo(movieDto1.getActor());
        assertThat(findMovie1.getDirector()).isEqualTo(movieDto1.getDirector());

        assertThat(findMovie2.getActor()).isEqualTo(movieDto2.getActor());
        assertThat(findMovie2.getDirector()).isEqualTo(movieDto2.getDirector());
    }

    @Test
    public void 도서_전체조회() throws Exception{
        MovieDto movieDto1 = new MovieDto("마블","울버린","엑스맨",10000,250);
        MovieDto movieDto2 = new MovieDto("류승룡","류승범","사생결단",9900,200);

        Long saveById1 = movieService.movieCreate(movieDto1);
        Long saveById2 = movieService.movieCreate(movieDto2);

        List<MovieDto> movieList = movieService.movieFindAll();

        assertThat(movieList).extracting("director").containsExactly("마블","류승룡");
        assertThat(movieList).extracting("price").containsExactly(10000,9900);
    }

    @Test
    public void 도서_수정() throws  Exception{
        MovieDto movieDto1 = new MovieDto("마블","울버린","엑스맨",10000,250);

        Long saveById1 = movieService.movieCreate(movieDto1);
        MovieDto findMovie1 = movieService.movieFindOne(saveById1);

        findMovie1.setDirector("크라노");

        Long upateBook1Id = movieService.movieUpdate(findMovie1);

        em.flush();
        em.clear();

        MovieDto updateFindMovie1 = movieService.movieFindOne(upateBook1Id);

        assertThat(updateFindMovie1.getDirector()).isEqualTo("크라노");


    }
}