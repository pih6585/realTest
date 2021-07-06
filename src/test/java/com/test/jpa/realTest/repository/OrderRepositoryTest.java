package com.test.jpa.realTest.repository;

import com.test.jpa.realTest.entity.Delivery;
import com.test.jpa.realTest.entity.Member;
import com.test.jpa.realTest.entity.Order;
import com.test.jpa.realTest.entity.OrderItem;
import com.test.jpa.realTest.entity.itemTable.Book;
import com.test.jpa.realTest.entity.itemTable.Item;
import com.test.jpa.realTest.enumClass.DeliveryStatus;
import com.test.jpa.realTest.enumClass.OrderStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired OrderRepository orderRepository;

    @Test
    public void 주문_저장() throws  Exception{
        Member createMember = Member.createMember("member1","서울","천호","111-11");
        em.persist(createMember);

        Delivery createDelivery = Delivery.createDelivery( createMember.getAddress(), DeliveryStatus.READY);

        Item book1 = createBook("JPA",10000,1,"김영한","12345567");
        Item book2 = createBook("SPRING",10000,1,"김영한","12345567");
        em.persist(book1);
        em.persist(book2);

        OrderItem orderItem1 = initOrderItem(book1,1);
        OrderItem orderItem2 = initOrderItem(book2,1);
        Order createOrder = Order.createOrder(createMember,  createDelivery, orderItem1, orderItem2);

        Order saveOrder = orderRepository.save(createOrder);

        em.flush();
        em.clear();

        Order findOrder = orderRepository.getById(saveOrder.getId());

        assertThat(findOrder.getStatus()).isEqualTo(OrderStatus.ORDER);
        assertThat(findOrder.getDelivery().getAddress().getCity()).isEqualTo("서울");
        assertThat(findOrder.getOrderItems().get(0).getOrderPrice()).isEqualTo(10000);

    };

    //주문_단일조회

    //주문_전체조회


    private Item createBook(String name, int price, int stockQuantity, String author, String isbn) {
        Book book = Book.bookCreate(author,isbn,name,price,stockQuantity);
        return book;
    }

    private OrderItem initOrderItem(Item item, int count) {
        OrderItem orderItem = OrderItem.createOrderItem(item,count);
        return orderItem;
    }
}