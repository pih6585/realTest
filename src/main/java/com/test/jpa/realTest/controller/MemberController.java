package com.test.jpa.realTest.controller;

import com.test.jpa.realTest.dto.MemberDto;
import com.test.jpa.realTest.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public class MemberController {


    @Autowired
    private MemberService memberService;

    @GetMapping("members/new")
    public String createForm(Model model){
        model.addAttribute("memberDto",new MemberDto());
        return "members/createMemberForm";
    }

    @PostMapping("members/new")
    public String create(@Valid MemberDto memberDto, BindingResult result) {
        if(result.hasErrors()){
            return "members/createMemberForm";
        }

        memberService.memberCreate(memberDto);
        return "redirect:/";
    }

    @PostMapping("members/update")
    public String update(@Valid MemberDto memberDto, BindingResult result) {
        if(result.hasErrors()){
            return "members/updateMemberForm";
        }

        memberService.memberUpdate(memberDto);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model, Pageable pageable){
        Page<MemberDto> memberList = memberService.memberFindAllPaging(pageable);
        model.addAttribute("members",memberList);
        model.addAttribute("memberList",memberList);
        return "members/memberList";
    }

    @GetMapping("members/{id}/edit")
    public String upateForm(@PathVariable("id") Long id, Model model){
        MemberDto memberDto = memberService.memberFindOne(id);
        model.addAttribute("memberDto",memberDto);
        return "members/updateMemberForm";
    }
}
