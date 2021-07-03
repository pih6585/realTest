package com.test.jpa.realTest.service;

import com.test.jpa.realTest.dto.MemberDto;
import com.test.jpa.realTest.entity.Member;
import com.test.jpa.realTest.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    @Transactional
    public Long memberUpdate(MemberDto memberDto){
        Optional<Member> optMember = memberRepository.findById(memberDto.getId());
        Member findMember = Optional.ofNullable(optMember.get()).get();
        System.out.println(memberDto.getId());
        System.out.println(memberDto.getName());
        System.out.println(memberDto.getCity());
        Member updateMember = findMember.updateMember(memberDto.getId(), memberDto.getName(), memberDto.getCity(), memberDto.getStreet(), memberDto.getZipcode());
        Member saveMember = memberRepository.save(updateMember);
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
