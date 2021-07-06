package com.test.jpa.realTest.repository;

import com.test.jpa.realTest.entity.Order;
import com.test.jpa.realTest.repository.query.OrderRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryQuery {

}
