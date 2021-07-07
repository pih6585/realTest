package com.test.jpa.realTest.entity;

import com.test.jpa.realTest.entity.itemTable.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
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

    protected void setInitOrder(Order order){
        this.order = order;
    }

    public static OrderItem createOrderItem(Item item, int count){
        OrderItem orderItem = new OrderItem(null,item,count);
        return orderItem;
    }

    private OrderItem(Long id, Item item, int count){
        this.id = id;
        this.item = item;
        this.orderPrice = item.getPrice()*count;
        this.count = count;
        item.orderCreateStock(count);
    }

    public void itemOrderCancel() {
        item.orderCancelStock(this.count);
    }
}
