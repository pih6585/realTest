package com.test.jpa.realTest.controller;

import com.test.jpa.realTest.dto.ItemDto;
import com.test.jpa.realTest.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("items/menuList")
    public String menuList(Model model){
        model.addAttribute("itemDto",new ItemDto());
        return "items/menuList";
    }

    @GetMapping("/items")
    public String list(Model model){
        List<ItemDto> itemList = itemService.itemList();
        model.addAttribute("itemDto",itemList);
        return "items/itemList";
    }



}
