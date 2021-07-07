package com.test.jpa.realTest.service;

import com.test.jpa.realTest.entity.Member;
import com.test.jpa.realTest.entity.Order;
import com.test.jpa.realTest.entity.itemTable.Book;
import com.test.jpa.realTest.entity.itemTable.Item;
import com.test.jpa.realTest.repository.ItemRepository;
import com.test.jpa.realTest.repository.MemberRepository;
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
class OrderServiceTest {

    @Autowired
    EntityManager em;

    @Autowired OrderService orderService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ItemRepository itemRepository;

    @Test
    public void 주문_저장() throws Exception{
        Member createMember = Member.createMember("member1","서울","천호","111-11");
        Member saveMember = memberRepository.save(createMember);

        Item book1 = Book.bookCreate("김영한","13123123","JPA",100000,100);
        Item saveItem = itemRepository.save(book1);


        Long findById = orderService.orderCreate(saveMember.getId(), saveItem.getId(), 2);

        Order findOrder = orderService.findOrderByOne(findById);

        assertThat(findOrder.getMember().getUsername()).isEqualTo("member1");
        assertThat(findOrder.getDelivery().getAddress().getStreet()).isEqualTo("천호");
        assertThat(findOrder.getOrderItems().get(0).getItem().getName()).isEqualTo("JPA");

    }

    @Test
    public void 주문_조회() throws  Exception{
        Member createMember = Member.createMember("member1","서울","천호","111-11");
        Member saveMember = memberRepository.save(createMember);

        Item book1 = Book.bookCreate("김영한","13123123","JPA",100000,100);
        Item saveItem = itemRepository.save(book1);


        Long findById = orderService.orderCreate(saveMember.getId(), saveItem.getId(), 2);

        em.flush();
        em.clear();

        orderService.findOrderAll();
    }

}