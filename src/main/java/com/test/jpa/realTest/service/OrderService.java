package com.test.jpa.realTest.service;

import com.test.jpa.realTest.dto.OrderDto;
import com.test.jpa.realTest.dto.OrderItemDto;
import com.test.jpa.realTest.entity.Delivery;
import com.test.jpa.realTest.entity.Member;
import com.test.jpa.realTest.entity.Order;
import com.test.jpa.realTest.entity.OrderItem;
import com.test.jpa.realTest.entity.itemTable.Item;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class OrderService {

    @Autowired OrderRepository orderRepository;

    @Autowired OrderItemRepository orderItemRepository;

    @Autowired MemberRepository memberRepository;

    @Autowired ItemRepository itemRepository;

    //주문 저장
    @Transactional
    public Long orderCreate(Long memberId, Long itemId,int count){
        Optional<Member> optMember = memberRepository.findById(memberId);
        Member findMember = Optional.ofNullable(optMember.get()).get();

        Delivery delivery = Delivery.createDelivery(findMember.getAddress(), DeliveryStatus.READY);

        Optional<Item> optItem = itemRepository.findById(itemId);
        Item findItem = Optional.ofNullable(optItem.get()).get();

        OrderItem orderItem = OrderItem.createOrderItem(findItem,count);

        Order order = Order.createOrder(findMember,delivery,orderItem);

        Order saveOrder = orderRepository.save(order);
        return saveOrder.getId();
    }

    //단건조회
    public Order findOrderByOne(Long id){
        return orderRepository.getById(id);
    }

    //주문 조회
    public List<OrderDto> findOrderAll(OrderDto schOrderDto){
        List<OrderDto> orderNotInItemList = orderRepository.findByAllByNotInItemDto(schOrderDto);

        //order id 뽑아오는 부분
        List<Long> orderIds = otderIdList(orderNotInItemList);

        //order의 item을 list로 받아오는 부분
        List<OrderItemDto> orderItemDtoList = orderItemRepository.findByOrderItemDto(orderIds);

        for (OrderDto orderDto : orderNotInItemList) {
            orderDto.setOrderItemList(getOrderItemList(orderDto.getOrderId(),orderItemDtoList));
        }

        return orderNotInItemList;
    }

    @Transactional
    public void orderCancel(Long orderId) {
        Optional<Order> optOrder = orderRepository.findById(orderId);
        Order findOrder = Optional.ofNullable(optOrder.get()).get();
        findOrder.orderCancel();
    }

    private List<OrderItemDto> getOrderItemList(Long orderId, List<OrderItemDto> orderItemDtoList) {
        List<OrderItemDto> getOrderItemList  = orderItemDtoList.stream().filter(orderItemDto -> orderItemDto.getOrderId().equals(orderId))
                .collect(Collectors.toList());
        return getOrderItemList;
    };

    private List<Long>  otderIdList(List<OrderDto> orderDtoList) {
        List<Long> orderIds = orderDtoList.stream().map(OrderDto::getOrderId).collect(Collectors.toList());
        return orderIds;
    }
}
