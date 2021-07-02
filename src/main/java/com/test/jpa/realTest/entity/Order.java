package com.test.jpa.realTest.entity;

import com.test.jpa.realTest.enumClass.OrderStatus;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "orders")
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    protected Order() {

    }

    public Order createOrder(Member member, List<OrderItem> orderItemList, Delivery delivery) {
        this.member = member;
        member.getOrderList().add(this);
        for (OrderItem orderItem : orderItemList) {
            this.orderItems.add(orderItem);
        }
        this.delivery = delivery;
        delivery.createOrder(this);
        this.orderDate = LocalDateTime.now();
        this.status = OrderStatus.ORDER;
        return this;
    }
}
