package com.test.jpa.realTest.repository;

import com.test.jpa.realTest.dto.OrderDto;
import com.test.jpa.realTest.dto.OrderItemDto;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired OrderRepository orderRepository;

    @Autowired OrderItemRepository orderItemRepository;

    @Test
    public void 주문_저장() throws  Exception{
        Member createMember = Member.createMember("member1","pih6585@nate.com" ,"1234","서울","천호","111-11");
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

    @Test
    public void 주문조회_제품포함() throws  Exception{
        Member createMember = Member.createMember("member1","pih6585@nate.com" ,"1234","서울","천호","111-11");
        em.persist(createMember);

        Delivery createDelivery = Delivery.createDelivery( createMember.getAddress(), DeliveryStatus.READY);

        Item book1 = createBook("JPA",10000,1,"김영한","12345567");
        Item book2 = createBook("SPRING",10000,1,"김영한","12345567");
        em.persist(book1);
        em.persist(book2);

        OrderItem orderItem1 = initOrderItem(book1,1);
        OrderItem orderItem2 = initOrderItem(book2,1);
        Order createOrder = Order.createOrder(createMember,  createDelivery, orderItem1,orderItem2);

        Order saveOrder = orderRepository.save(createOrder);

        em.flush();
        em.clear();

        OrderDto schOrderDto = new OrderDto();
        List<OrderDto> orderInItemList = orderRepository.findAllByInItemDto(schOrderDto);
      
        for (OrderDto orderDto : orderInItemList) {
            System.out.println(orderDto.getItemName());
        }

        assertThat(orderInItemList).extracting("itemName").containsExactly("JPA","SPRING");
        //무조건 복수로 담기기 떄문에 똑같은걸 계속 참조..하는 문제가 생김. 그리고 dto라 fetch조인도 쓸수없음.
        //distinct로 중복제거가 안됨. cross 조인이 나감...
        assertThat(orderInItemList).extracting("username").containsExactly("member1","member1");
        assertThat(orderInItemList).extracting("city").containsExactly("서울","서울");

    };

    @Test
    public void 주문조회_제품미포함() throws  Exception{
        Member createMember = Member.createMember("member1","pih6585@nate.com" ,"1234","서울","천호","111-11");
        em.persist(createMember);

        Delivery createDelivery = Delivery.createDelivery( createMember.getAddress(), DeliveryStatus.READY);

        Item book1 = createBook("JPA",10000,1,"김영한","12345567");
        Item book2 = createBook("SPRING",10000,1,"김영한","12345567");
        em.persist(book1);
        em.persist(book2);

        OrderItem orderItem1 = initOrderItem(book1,1);
        OrderItem orderItem2 = initOrderItem(book2,1);
        Order createOrder = Order.createOrder(createMember,  createDelivery, orderItem1,orderItem2);

        Order saveOrder = orderRepository.save(createOrder);

        em.flush();
        em.clear();

        OrderDto schOrderDto = new OrderDto();

        List<OrderDto> orderInItemList = orderRepository.findByAllByNotInItemDto(schOrderDto);

        for (OrderDto orderDto : orderInItemList) {
            System.out.println(orderDto.getItemName());
        }

        assertThat(orderInItemList).extracting("username").containsExactly("member1");
        assertThat(orderInItemList).extracting("city").containsExactly("서울");

    };

    //주문조회_제품미포함_별도_제품조회
    @Test
    public void 주문조회_제품미포함_별도_제품조회() throws  Exception{
        Member createMember = Member.createMember("member1","pih6585@nate.com" ,"1234","서울","천호","111-11");
        em.persist(createMember);

        Delivery createDelivery = Delivery.createDelivery( createMember.getAddress(), DeliveryStatus.READY);

        Item book1 = createBook("JPA",10000,1,"김영한","12345567");
        Item book2 = createBook("SPRING",10000,1,"김영한","12345567");
        em.persist(book1);
        em.persist(book2);

        OrderItem orderItem1 = initOrderItem(book1,1);
        OrderItem orderItem2 = initOrderItem(book2,1);
        Order createOrder = Order.createOrder(createMember,  createDelivery, orderItem1,orderItem2);

        Order saveOrder = orderRepository.save(createOrder);

        em.flush();
        em.clear();

        OrderDto schOrderDto = new OrderDto();
        //schOrderDto.setUsername("member1");
        //order list 가져오는 부분
        List<OrderDto> orderNotInItemList = orderRepository.findByAllByNotInItemDto(schOrderDto);

        //order id 뽑아오는 부분
        List<Long> orderIds = otderIdList(orderNotInItemList);

        //order의 item을 list로 받아오는 부분
        List<OrderItemDto> orderItemDtoList = orderItemRepository.findByOrderItemDto(orderIds);

        for (OrderDto orderDto : orderNotInItemList) {
            orderDto.setOrderItemList(getOrderItemList(orderDto.getOrderId(),orderItemDtoList));
        }

        assertThat(orderNotInItemList).extracting("username").containsExactly("member1");
        assertThat(orderNotInItemList).extracting("city").containsExactly("서울");
        assertThat(orderNotInItemList.get(0).getOrderItemList()).extracting("itemName").containsExactly("JPA","SPRING");

    }

    /*private List<OrderItemDto> getOrderItemList(Long orderId, List<OrderItemDto> orderItemDtoList) {
        List<OrderItemDto> getOrderItemList  = orderItemDtoList.stream().filter(orderItemDto -> orderItemDto.getOrderId().equals(orderId))
                                              .collect(Collectors.toList());
        return getOrderItemList;
    };*/

    private List<OrderItemDto> getOrderItemList(Long orderId, List<OrderItemDto> orderItemDtoList) {
        List<OrderItemDto> orderItemList  = new ArrayList<>();
        for(OrderItemDto orderItemDto : orderItemDtoList){
            if(orderItemDto.getOrderId().equals(orderId)){
                orderItemList.add(orderItemDto);
            }
        }
        return orderItemList;
    };

    private List<Long>  otderIdList(List<OrderDto> orderDtoList) {
        List<Long> orderIds = new ArrayList<>();
        for(OrderDto orderDto : orderDtoList){
            orderIds.add(orderDto.getOrderId());
        }
        //List<Long> orderIds = orderDtoList.stream().map(OrderDto::getOrderId).collect(Collectors.toList());
        return orderIds;
    }


   private Item createBook(String name, int price, int stockQuantity, String author, String isbn) {
        Book book = Book.bookCreate(author,isbn,name,price,stockQuantity);
        return book;
    }

    private OrderItem initOrderItem(Item item, int count) {
        OrderItem orderItem = OrderItem.createOrderItem(item,count);
        return orderItem;
    }
}