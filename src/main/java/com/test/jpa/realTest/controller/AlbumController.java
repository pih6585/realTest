package com.test.jpa.realTest.controller;

import com.test.jpa.realTest.dto.AlbumDto;
import com.test.jpa.realTest.service.AlbumService;
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
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @GetMapping("albums/new")
    public String createForm(Model model){
        model.addAttribute("albumDto",new AlbumDto());
        return "items/createAlbumForm";
    }

    @PostMapping("albums/new")
    public String create(AlbumDto albumDto, BindingResult result){
        if(result.hasErrors()){
            return "items/createAlbumForm";
        }
        albumService.albumCreate(albumDto);
        return "redirect:/";
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
        return "redirect:/";
    }

}
