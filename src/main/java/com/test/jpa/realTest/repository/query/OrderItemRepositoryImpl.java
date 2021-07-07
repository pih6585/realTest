package com.test.jpa.realTest.repository.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.test.jpa.realTest.dto.OrderItemDto;
import com.test.jpa.realTest.dto.QOrderItemDto;
import com.test.jpa.realTest.entity.QOrderItem;
import com.test.jpa.realTest.entity.itemTable.QItem;

import javax.persistence.EntityManager;
import java.util.List;

import static com.test.jpa.realTest.entity.QOrderItem.orderItem;
import static com.test.jpa.realTest.entity.itemTable.QItem.item;

public class OrderItemRepositoryImpl implements OrderItemRepositoryQuery{

    private final EntityManager em;

    private final JPAQueryFactory queryFactory;

    public OrderItemRepositoryImpl(EntityManager em) {
        this.em = em;
        queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<OrderItemDto> findByOrderItemDto(List<Long> orderIds) {
        return queryFactory
                .select(new QOrderItemDto(orderItem.order.id,item.name, orderItem.orderPrice, orderItem.count))
                .from(orderItem)
                .join(orderItem.item, item)
                .where(orderItem.order.id.in(orderIds))
                .fetch();
    }
}
