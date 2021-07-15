package com.test.jpa.realTest.repository;

import com.test.jpa.realTest.entity.Category;
import com.test.jpa.realTest.repository.query.CategoryRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryRepositoryQuery {

}
