package com.test.jpa.realTest.repository.query;

import com.test.jpa.realTest.dto.OrderItemDto;

import java.util.List;

public interface OrderItemRepositoryQuery {
    List<OrderItemDto> findByOrderItemDto(List<Long> orderIds);
}
