package com.test.jpa.realTest.repository;

import com.test.jpa.realTest.entity.itemTable.Movie;
import com.test.jpa.realTest.repository.query.MovieRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long>, MovieRepositoryQuery {
}
