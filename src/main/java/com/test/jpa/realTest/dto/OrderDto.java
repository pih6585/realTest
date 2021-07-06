package com.test.jpa.realTest.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.test.jpa.realTest.enumClass.DeliveryStatus;
import com.test.jpa.realTest.enumClass.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {

    private Long orderId;
    private String username;
    private String city;
    private String street;
    private String zipcode;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private DeliveryStatus deliveryStatus;
    private List<OrderItemDto> orderItemList;
    private String itemName;
    private int orderPrice;
    private int count;

    public OrderDto(String username, String city, String street, String zipcode) {
        this.username = username;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    @QueryProjection
    public OrderDto(Long orderId, String username, String city, String street, String zipcode, LocalDateTime orderDate, OrderStatus orderStatus,
                    DeliveryStatus deliveryStatus) {
        this.orderId = orderId;
        this.username = username;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.deliveryStatus = deliveryStatus;
    }

    @QueryProjection
    public OrderDto(Long orderId, String username, String city, String street, String zipcode, String itemName, int orderPrice, int count,
                    LocalDateTime orderDate, OrderStatus orderStatus, DeliveryStatus deliveryStatus) {
        this.orderId = orderId;
        this.username = username;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
        this.itemName = itemName;
        this.orderPrice = orderPrice;
        this.count = count;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.deliveryStatus = deliveryStatus;
    }

}
