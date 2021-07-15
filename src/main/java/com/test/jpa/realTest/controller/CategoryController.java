package com.test.jpa.realTest.controller;

import com.test.jpa.realTest.dto.CategoryDto;
import com.test.jpa.realTest.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CategoryController {


    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories/new")
    public String createForm(Model model){
        List<CategoryDto> parentList = categoryService.findByParentIdList();
        model.addAttribute("categoryDto",new CategoryDto());
        model.addAttribute("categories",parentList);
        return "categories/categoryForm";
    }

    @PostMapping("/categories/new")
    public String create(@RequestParam(value = "parentId", defaultValue = "0") Long parentId, @Valid CategoryDto categoryDto, BindingResult result){
        if(result.hasErrors()){
            return "categories/categoryForm";
        }
        categoryService.category_create(categoryDto,parentId);
        return "redirect:/home";
    }

    @GetMapping("/categories")
    public String list(Model model){
        List<CategoryDto> categoryList = categoryService.findByAll();
        model.addAttribute("categoryDto",categoryList);
        return "categories/categoryList";
    }
}
