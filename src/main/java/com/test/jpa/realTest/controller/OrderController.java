package com.test.jpa.realTest.controller;

import com.test.jpa.realTest.dto.ItemDto;
import com.test.jpa.realTest.dto.MemberDto;
import com.test.jpa.realTest.dto.OrderDto;
import com.test.jpa.realTest.service.ItemService;
import com.test.jpa.realTest.service.MemberService;
import com.test.jpa.realTest.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
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
        List<ItemDto> bookList = itemService.bookList();
        List<ItemDto> albumList = itemService.albumList();
        List<ItemDto> movieList = itemService.movieList();
        model.addAttribute("members",memberList);
        model.addAttribute("books",bookList);
        model.addAttribute("albums",albumList);
        model.addAttribute("movies",movieList);
        return "order/orderForm";
    }

    @PostMapping("order/new")
    public String create(@RequestParam("memberId") Long memberId, @RequestParam(value = "bookId", defaultValue = "0")  Long bookId,
                         @RequestParam(value = "albumId", defaultValue = "0")  Long albumId,
                         @RequestParam(value = "movieId", defaultValue = "0") Long movieId,
                         @RequestParam(value = "count1", defaultValue = "1") int count1,
                         @RequestParam(value = "count2", defaultValue = "1") int count2,
                         @RequestParam(value = "count3", defaultValue = "1")   int count3){
        orderService.orderCreate_allType(memberId,bookId,count1,albumId,count2,movieId,count3);
        return "redirect:/";
    }

    @GetMapping("orders")
    public String orderList(@ModelAttribute("orderSearch") OrderDto orderDto, Model model){
       List<OrderDto> orderList = orderService.findOrderAll(orderDto);
        //List<OrderDto> orderList = orderService.findOrderAllWithItem(orderDto);
        model.addAttribute("orders",orderList);
        return "order/orderList";
    }

    @PostMapping("orders/{id}/cancel")
    public String orderCancel(@PathVariable("id") Long orderId, @ModelAttribute("orderSearch") OrderDto orderDto, Model model){
        orderService.orderCancel(orderId);
        List<OrderDto> orderList = orderService.findOrderAll(orderDto);
        model.addAttribute("orders",orderList);
        return "order/orderList";
    }

}
