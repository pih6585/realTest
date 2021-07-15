package com.test.jpa.realTest.repository;

import com.test.jpa.realTest.entity.CategoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryItemRepository extends JpaRepository<CategoryItem, Long> {
}
