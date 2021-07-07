package com.test.jpa.realTest.repository;

import com.test.jpa.realTest.entity.OrderItem;
import com.test.jpa.realTest.repository.query.OrderItemRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>, OrderItemRepositoryQuery {
}
