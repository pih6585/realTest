package com.test.jpa.realTest.service;

import com.test.jpa.realTest.dto.OrderDto;
import com.test.jpa.realTest.dto.OrderItemDto;
import com.test.jpa.realTest.entity.Delivery;
import com.test.jpa.realTest.entity.Member;
import com.test.jpa.realTest.entity.Order;
import com.test.jpa.realTest.entity.OrderItem;
import com.test.jpa.realTest.entity.itemTable.Item;
import com.test.jpa.realTest.entity.itemTable.Movie;
import com.test.jpa.realTest.enumClass.DeliveryStatus;
import com.test.jpa.realTest.repository.ItemRepository;
import com.test.jpa.realTest.repository.MemberRepository;
import com.test.jpa.realTest.repository.OrderItemRepository;
import com.test.jpa.realTest.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ItemRepository itemRepository;

    //주문 저장
    @Transactional
    public Long orderCreate(Long memberId, Long itemId, int count) {
        Delivery delivery = Delivery.createDelivery(searchMember(memberId).getAddress(), DeliveryStatus.READY);

        OrderItem orderItem = findItemById(itemId, count);

        Order order = Order.createOrder(searchMember(memberId), delivery, orderItem);

        Order saveOrder = orderRepository.save(order);
        return saveOrder.getId();
    }

    @Transactional
    public Long orderCreate_allType(Long memberId, Long bookId, int count1, Long albumId, int count2, Long movieId, int count3) {
        Delivery delivery = Delivery.createDelivery(searchMember(memberId).getAddress(), DeliveryStatus.READY);
        OrderItem orderItem1 = findItemById(bookId, count1);
        OrderItem orderItem2 = findItemById(albumId, count2);
        OrderItem orderItem3 = findItemById(movieId, count3);

        Order order = Order.createOrder(searchMember(memberId), delivery, orderItem1, orderItem2, orderItem3);

        Order saveOrder = orderRepository.save(order);
        return saveOrder.getId();
    }

    private OrderItem findItemById(Long itemId, int count) {
        Optional<Item> optItem = itemRepository.findById(itemId);
        OrderItem orderItem = null;
        if (optItem.isPresent()) {
            Item findItem = Optional.ofNullable(optItem.get()).get();
            orderItem = OrderItem.createOrderItem(findItem, count);
        }
        return orderItem;
    }

    private Member searchMember(Long memberId) {
        Optional<Member> optMember = memberRepository.findById(memberId);
        Member member = Optional.ofNullable(optMember.get()).get();
        return member;
    }

    //단건조회
    public Order findOrderByOne(Long id) {
        return orderRepository.getById(id);
    }

    //주문 조회
    public List<OrderDto> findOrderAll(OrderDto schOrderDto) {
        List<OrderDto> orderNotInItemList = orderRepository.findByAllByNotInItemDto(schOrderDto);

        //order id 뽑아오는 부분
        List<Long> orderIds = otderIdList(orderNotInItemList);

        //order의 item을 list로 받아오는 부분
        List<OrderItemDto> orderItemDtoList = orderItemRepository.findByOrderItemDto(orderIds);

        for (OrderDto orderDto : orderNotInItemList) {
            orderDto.setOrderItemList(getOrderItemList(orderDto.getOrderId(), orderItemDtoList));
        }

        System.out.println(orderNotInItemList.size());
        return orderNotInItemList;
    }

    //주문조회_제품 join
    public List<OrderDto> findOrderAllWithItem(OrderDto schOrderDto) {
        List<OrderDto> orderInItemList = orderRepository.findAllByInItemDto(schOrderDto);
        return orderInItemList;
    }

    @Transactional
    public void orderCancel(Long orderId) {
        Optional<Order> optOrder = orderRepository.findById(orderId);
        Order findOrder = Optional.ofNullable(optOrder.get()).get();
        findOrder.orderCancel();
    }

    private List<OrderItemDto> getOrderItemList(Long orderId, List<OrderItemDto> orderItemDtoList) {
        List<OrderItemDto> getOrderItemList = orderItemDtoList.stream().filter(orderItemDto -> orderItemDto.getOrderId().equals(orderId))
                .collect(Collectors.toList());
        return getOrderItemList;
    };


    private List<Long> otderIdList(List<OrderDto> orderDtoList) {
        List<Long> orderIds = orderDtoList.stream().map(OrderDto::getOrderId).collect(Collectors.toList());
        return orderIds;
    }
}
