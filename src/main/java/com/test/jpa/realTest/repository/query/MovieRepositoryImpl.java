package com.test.jpa.realTest.repository.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.test.jpa.realTest.dto.MovieDto;
import com.test.jpa.realTest.dto.QMovieDto;
import com.test.jpa.realTest.entity.itemTable.QMovie;

import javax.persistence.EntityManager;
import java.util.List;

import static com.test.jpa.realTest.entity.itemTable.QMovie.movie;

public class MovieRepositoryImpl implements MovieRepositoryQuery {

    private final EntityManager em;

    private final JPAQueryFactory queryFactory;

    public MovieRepositoryImpl(EntityManager em) {
        this.em = em;
        queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public MovieDto findByOneByDto(Long id) {
        MovieDto movieDto = queryFactory
                .select(new QMovieDto(movie.id, movie.director, movie.actor, movie.name, movie.price, movie.stockQuantity, movie.itemDtype))
                .from(movie)
                .where(movie.id.eq(id))
                .fetchOne();
        return movieDto;
    }

    @Override
    public List<MovieDto> findByAllByDto() {
        List<MovieDto> movieList = queryFactory
                .select(new QMovieDto(movie.id, movie.director, movie.actor, movie.name, movie.price, movie.stockQuantity, movie.itemDtype))
                .from(movie)
                .fetch();
        return movieList;
    }
}
