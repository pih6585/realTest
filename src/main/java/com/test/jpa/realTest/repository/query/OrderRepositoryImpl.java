package com.test.jpa.realTest.repository.query;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.test.jpa.realTest.dto.OrderDto;
import com.test.jpa.realTest.dto.QOrderDto;
import com.test.jpa.realTest.entity.QDelivery;
import com.test.jpa.realTest.entity.QMember;
import com.test.jpa.realTest.entity.QOrder;
import com.test.jpa.realTest.entity.QOrderItem;
import com.test.jpa.realTest.enumClass.DeliveryStatus;
import com.test.jpa.realTest.enumClass.OrderStatus;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static com.test.jpa.realTest.entity.QDelivery.delivery;
import static com.test.jpa.realTest.entity.QMember.member;
import static com.test.jpa.realTest.entity.QOrder.order;
import static com.test.jpa.realTest.entity.QOrderItem.orderItem;
import static com.test.jpa.realTest.entity.itemTable.QMovie.movie;

public class OrderRepositoryImpl implements OrderRepositoryQuery{

    private final EntityManager em;

    private final JPAQueryFactory queryFactory;

    public OrderRepositoryImpl(EntityManager em) {
        this.em = em;
        queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public List<OrderDto> findAllByInItemDto(OrderDto orderDto) {
        List<OrderDto> orderDtoList = queryFactory
                .select(new QOrderDto(order.id,
                        member.username,
                        delivery.address.city, delivery.address.street, delivery.address.zipcode,
                        orderItem.item.name, orderItem.orderPrice, orderItem.count, order.orderDate, order.status, delivery.status
                ))
                .from(order)
                .join(order.member, member)
                .join(order.delivery, delivery)
                .join(order.orderItems, orderItem)
                .where(memberNameEq(orderDto.getUsername()),orderStatusEq(orderDto.getOrderStatus()))
                .fetch();
        return orderDtoList;
    }


    @Override
    public List<OrderDto> findByAllByNotInItemDto(OrderDto orderDto) {
        List<OrderDto> orderDtoList = queryFactory
                .select(new QOrderDto(order.id,
                        member.username,
                        delivery.address.city, delivery.address.street, delivery.address.zipcode,
                        order.orderDate, order.status, delivery.status
                ))
                .from(order)
                .join(order.member, member)
                .join(order.delivery, delivery)
                .where(memberNameEq(orderDto.getUsername()),orderStatusEq(orderDto.getOrderStatus()))
                .fetch();
        return orderDtoList;
    }

    private BooleanExpression orderStatusEq(OrderStatus orderStatus) {
        return orderStatus == null ? null : order.status.eq(orderStatus);
    }

    private BooleanExpression memberNameEq(String username) {
        return username == null ? null : username.equals("") ? null : order.member.username.eq(username);
    }
}
