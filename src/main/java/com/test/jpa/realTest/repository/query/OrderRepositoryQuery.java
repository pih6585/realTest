package com.test.jpa.realTest.repository.query;

import com.test.jpa.realTest.dto.OrderDto;

import java.util.List;

public interface OrderRepositoryQuery {

    //전체 조인 - 전체
    List<OrderDto> findAllByInItemDto();

    //제품조인 - 제외 - 전체
    List<OrderDto> findByAllByNotInItemDto();

}
