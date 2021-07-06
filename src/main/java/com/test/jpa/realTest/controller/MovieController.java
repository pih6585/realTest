package com.test.jpa.realTest.controller;

import com.test.jpa.realTest.dto.MovieDto;
import com.test.jpa.realTest.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("movies/new")
    public String createForm(Model model){
        model.addAttribute("movieDto",new MovieDto());
        return "items/createMovieForm";
    }

    @PostMapping("movies/new")
    public String create(@Valid MovieDto movieDto, BindingResult result){
        if(result.hasErrors()){
            return "items/createMovieForm";
        }
        movieService.movieCreate(movieDto);
        return "redirect:/";
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
        return "redirect:/";
    }

}
