package com.test.jpa.realTest.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class OrderItemDto {

    private Long orderId;

    private String itemName;

    private int orderPrice;

    private int count;

    public OrderItemDto(){

    }

    @QueryProjection
    public OrderItemDto( String itemName, int orderPrice, int count){
        this.itemName = itemName;
        this.orderPrice = orderPrice;
        this.count = count;
    }

    @QueryProjection
    public OrderItemDto(Long orderId, String itemName, int orderPrice, int count){
        this.orderId = orderId;
        this.itemName = itemName;
        this.orderPrice = orderPrice;
        this.count = count;
    }


}
