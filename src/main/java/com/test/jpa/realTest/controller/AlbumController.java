package com.test.jpa.realTest.controller;

import com.test.jpa.realTest.dto.AlbumDto;
import com.test.jpa.realTest.dto.CategoryDto;
import com.test.jpa.realTest.service.AlbumService;
import com.test.jpa.realTest.service.CategoryService;
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
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("albums/new")
    public String createForm(Model model){
        List<CategoryDto> childList = categoryService.getChildList();
        model.addAttribute("albumDto",new AlbumDto());
        model.addAttribute("categories",childList);
        return "items/createAlbumForm";
    }

    @PostMapping("albums/new")
    public String create(@RequestParam("categoryId") @Nullable Long categoryId, AlbumDto albumDto, BindingResult result){
        if(result.hasErrors()|| categoryId == null){
            return "items/createAlbumForm";
        }
        albumService.albumCreateWithCategory(albumDto, categoryId);
        return "redirect:/home";
    }

    @GetMapping("albums/{id}/edit")
    public String updateForm(@PathVariable("id") Long id, Model model){
        AlbumDto albumDto = albumService.albumFindOne(id);
        model.addAttribute("albumDto",albumDto);
        return "items/updateAlbumForm";
    }

    @PostMapping("albums/update")
    public String upate(@Valid AlbumDto albumDto, BindingResult result) {
        if (result.hasErrors()) {
            return "items/updateAlbumForm";
        }
        albumService.albumUpdate(albumDto);
        return "redirect:/home";
    }

}
