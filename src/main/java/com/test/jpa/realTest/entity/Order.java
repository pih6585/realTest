package com.test.jpa.realTest.entity;

import com.test.jpa.realTest.enumClass.OrderStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public static Order createOrder(Member member, Delivery delivery,OrderItem... orderItemList) {
        Order order = new Order(null,member, delivery, orderItemList);
        return order;
    }

    public static Order updateOrder(Long id, Member member, Delivery delivery,OrderItem... orderItemList) {
        Order order = new Order(id,member, delivery, orderItemList);
        return order;
    }

    private Order(Long id, Member member, Delivery delivery,OrderItem... orderItemList){
        if(id != null){
            this.id = id;
        }
        this.member = member;
        member.getOrderList().add(this);
        for (OrderItem orderItem : orderItemList) {
            this.orderItems.add(orderItem);
            orderItem.setInitOrder(this);
        }
        this.delivery = delivery;
        delivery.createOrder(this);
        this.orderDate = LocalDateTime.now();
        this.status = OrderStatus.ORDER;
    }
}
