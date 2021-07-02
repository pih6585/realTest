package com.test.jpa.realTest.service;

import com.test.jpa.realTest.dto.MemberDto;
import com.test.jpa.realTest.entity.Member;
import com.test.jpa.realTest.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Transactional
    public Long memberCreate(MemberDto memberDto){
        Member member = Member.createMember(memberDto.getName(), memberDto.getCity(), memberDto.getStreet(), memberDto.getZipcode());
        Member saveMember = memberRepository.save(member);
        return saveMember.getId();
    }


    public MemberDto memberFindOne(Long id){
        MemberDto memberDto = memberRepository.findByOneByDto(id);
        return memberDto;
    }

    public List<MemberDto> memberFindAll(){
        List<MemberDto> memberDtoList = memberRepository.findByAllByDto();
        return memberDtoList;
    }

}
