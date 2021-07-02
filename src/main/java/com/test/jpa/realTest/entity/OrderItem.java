package com.test.jpa.realTest.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "orderitems_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;

    private int count;

    public OrderItem createOrderItem(Long id, Item item, int count){
        this.id = id;
        this.item = item;
        this.orderPrice = item.getPrice()*count;
        this.count = count;
        return this;
    }
}
