package com.test.jpa.realTest.controller;

import com.test.jpa.realTest.dto.BookDto;
import com.test.jpa.realTest.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("books/new")
    public String createForm(Model model){
        model.addAttribute("bookDto",new BookDto());
        return "items/createBookForm";
    }

    @PostMapping("books/new")
    public String create(@Valid BookDto bookDto, BindingResult result){
        if(result.hasErrors()){
            return "items/createBookForm";
        }
        bookService.bookCreate(bookDto);
        return "redirect:/home";
    }

    @GetMapping("books/{id}/edit")
    public String updateForm(@PathVariable("id") Long id, Model model){
        BookDto bookDto = bookService.bookFindOne(id);
        model.addAttribute("bookDto",bookDto);
        return "items/updateBookForm";
    }

    @PostMapping("books/update")
    public String update(@Valid BookDto bookDto, BindingResult result){
        if(result.hasErrors()){
            return "items/updateBookForm";
        }
        bookService.bookUpdate(bookDto);
        return "redirect:/home";
    }

}
