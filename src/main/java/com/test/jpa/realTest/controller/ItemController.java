package com.test.jpa.realTest.controller;

import com.test.jpa.realTest.entity.itemTable.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
public class ItemController {

    @GetMapping("items/new")
    public String createForm(Model model){
        Book book = Book.bookCreate(null,null,null,0,0);
        model.addAttribute("form",book);
        return "items/createItemForm";
    }

}
