package com.test.jpa.realTest.repository;

import com.test.jpa.realTest.entity.itemTable.Movie;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MovieRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired MovieRepository movieRepository;

    @Test
    public void 영화_등록() throws Exception{
        Movie movie1 = Movie.movieCreate("마블","울버린","엑스맨",10000,250);
        Movie movie2 = Movie.movieCreate("류승룡","류승범","사생결단",9900,200);

        Movie saveMovie1 = movieRepository.save(movie1);
        Movie saveMovie2 = movieRepository.save(movie2);

        assertThat(saveMovie1.getDirector()).isEqualTo("마블");
        assertThat(saveMovie1.getPrice()).isGreaterThan(9000);

        assertThat(saveMovie2.getActor()).isEqualTo("류승범");
        assertThat(saveMovie2.getStockQuantity()).isEqualTo(200);

    }

    @Test
    public void 영화_단일조회() throws Exception{
        Movie movie1 = Movie.movieCreate("마블","울버린","엑스맨",10000,250);
        Movie movie2 = Movie.movieCreate("류승룡","류승범","사생결단",9900,200);

        Movie saveMovie1 = movieRepository.save(movie1);
        Movie saveMovie2 = movieRepository.save(movie2);

        Optional<Movie> optMovie1 = movieRepository.findById(saveMovie1.getId());
        Movie findMovie1 = Optional.ofNullable(optMovie1.get()).get();

        Optional<Movie> optMovie2 = movieRepository.findById(saveMovie2.getId());
        Movie findMovie2 = Optional.ofNullable(optMovie2.get()).get();

        assertThat(findMovie1.getName()).isEqualTo("엑스맨");
        assertThat(findMovie1).isEqualTo(saveMovie1);

        assertThat(findMovie2.getName()).isEqualTo("사생결단");
        assertThat(findMovie2).isEqualTo(saveMovie2);

    }

    @Test
    public void 영화_전체조회() throws Exception{
        Movie movie1 = Movie.movieCreate("마블","울버린","엑스맨",10000,250);
        Movie movie2 = Movie.movieCreate("류승룡","류승범","사생결단",9900,200);

        Movie saveMovie1 = movieRepository.save(movie1);
        Movie saveMovie2 = movieRepository.save(movie2);

        List<Movie> movieList = movieRepository.findAll();

        assertThat(movieList).extracting("director").containsExactly("마블","류승룡");
        assertThat(movieList).extracting("name").containsExactly("엑스맨","사생결단");

    }

}