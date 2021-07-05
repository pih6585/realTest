package com.test.jpa.realTest.repository;

import com.test.jpa.realTest.dto.BookDto;
import com.test.jpa.realTest.entity.itemTable.Book;
import com.test.jpa.realTest.repository.query.BookRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long>, BookRepositoryQuery {

}
