package com.test.jpa.realTest.controller;

import com.test.jpa.realTest.dto.ItemDto;
import com.test.jpa.realTest.dto.MemberDto;
import com.test.jpa.realTest.dto.OrderDto;
import com.test.jpa.realTest.service.ItemService;
import com.test.jpa.realTest.service.MemberService;
import com.test.jpa.realTest.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    MemberService memberService;

    @Autowired
    ItemService itemService;

    @GetMapping("order/new")
    public String createForm(Model model){
        List<MemberDto> memberList = memberService.memberFindAll();
        List<ItemDto> itemList = itemService.itemList();
        model.addAttribute("members",memberList);
        model.addAttribute("items",itemList);
        return "order/orderForm";
    }

    @PostMapping("order/new")
    public String create(@RequestParam("memberId") Long memberId, @RequestParam("itemId") Long itemId,
                        @RequestParam("count") int count){
        orderService.orderCreate(memberId,itemId,count);
        return "redirect:/";
    }

    @GetMapping("orders")
    public String orderList(@ModelAttribute("orderSearch") OrderDto orderDto, Model model){
        List<OrderDto> orderList = orderService.findOrderAll(orderDto);
        model.addAttribute("orders",orderList);
        return "order/orderList";
    }

    @PostMapping("orders/{id}/cancel")
    public String orderCancel(@PathVariable("id") Long orderId, @ModelAttribute("orderSearch") OrderDto orderDto, Model model){
        System.out.println(orderId+"=====================orderId=====================");
        orderService.orderCancel(orderId);
        List<OrderDto> orderList = orderService.findOrderAll(orderDto);
        model.addAttribute("orders",orderList);
        return "order/orderList";
    }

}
