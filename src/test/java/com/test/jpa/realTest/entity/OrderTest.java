package com.test.jpa.realTest.entity;

import com.test.jpa.realTest.entity.itemTable.Book;
import com.test.jpa.realTest.entity.itemTable.Item;
import com.test.jpa.realTest.enumClass.DeliveryStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderTest {

    @Test
    public void 주문_등록()throws Exception{
        Member createMember = Member.createMember("member1","pih6585@nate.com" ,"1234","서울","천호","111-11");
        Delivery createDelivery = Delivery.createDelivery( createMember.getAddress(), DeliveryStatus.READY);
        Item book1 = createBook(1L,"JPA",10000,1,"김영한","12345567");
        Item book2 = createBook(1L,"SPRING",10000,1,"김영한","12345567");
        OrderItem orderItem1 = initOrderItem(1L,book1,1);
        OrderItem orderItem2 = initOrderItem(2L,book2,1);
        Order createOrder = Order.createOrder(createMember,  createDelivery, orderItem1, orderItem2);

        assertThat(createOrder.getMember().getUsername()).isEqualTo("member1");
        assertThat(createOrder.getDelivery().getAddress().getCity()).isEqualTo("서울");
        assertThat(createOrder.getOrderItems()).extracting("orderPrice").containsExactly(10000,10000);
    }

    private Item createBook(Long id, String name, int price, int stockQuantity, String author, String isbn) {
        Book book = Book.bookCreate(author,isbn,name,price,stockQuantity);
        return book;
    }

    private OrderItem initOrderItem(Long id,Item item, int count) {
        OrderItem orderItem = OrderItem.createOrderItem(item,count);
        return orderItem;
    }
}