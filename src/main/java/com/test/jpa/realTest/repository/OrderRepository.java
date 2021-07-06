package com.test.jpa.realTest.repository;

import com.test.jpa.realTest.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
