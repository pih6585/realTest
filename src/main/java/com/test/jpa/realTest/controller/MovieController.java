package com.test.jpa.realTest.controller;

import com.test.jpa.realTest.dto.CategoryDto;
import com.test.jpa.realTest.dto.MovieDto;
import com.test.jpa.realTest.service.CategoryService;
import com.test.jpa.realTest.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("movies/new")
    public String createForm(Model model){
        List<CategoryDto> childList = categoryService.getChildList();
        model.addAttribute("movieDto",new MovieDto());
        model.addAttribute("categories",childList);
        return "items/createMovieForm";
    }

    @PostMapping("movies/new")
    public String create(@RequestParam("categoryId") @Nullable Long categoryId, @Valid MovieDto movieDto, BindingResult result){
        if(result.hasErrors() || categoryId == null){
            return "items/createMovieForm";
        }
        movieService.movieCreateWithCategory(movieDto, categoryId);
        return "redirect:/home";
    }

    @GetMapping("movies/{id}/edit")
    public String updateForm(@PathVariable("id") Long id, Model model){
        MovieDto movieDto = movieService.movieFindOne(id);
        model.addAttribute("movieDto",movieDto);
        return "items/updateMovieForm";
    }

    @PostMapping("movies/update")
    public String update(@Valid MovieDto movieDto, BindingResult result){
        if(result.hasErrors()){
            return "items/updateMovieForm";
        }
        movieService.movieUpdate(movieDto);
        return "redirect:/home";
    }

}
