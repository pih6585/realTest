package com.test.jpa.realTest.service;

import com.test.jpa.realTest.dto.MemberDto;
import com.test.jpa.realTest.entity.Member;
import com.test.jpa.realTest.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        Member member = Member.createMember(memberDto.getName(), memberDto.getEmail(), memberDto.getPassword(),
                                            memberDto.getCity(), memberDto.getStreet(), memberDto.getZipcode());
        Member saveMember = memberRepository.save(member);
        return saveMember.getId();
    }

    @Transactional
    public Long memberUpdate(MemberDto memberDto){
        Optional<Member> optMember = memberRepository.findById(memberDto.getId());
        Member findMember = Optional.ofNullable(optMember.get()).get();
        Member updateMember = findMember.updateMember(memberDto.getId(), memberDto.getName(), memberDto.getEmail(), memberDto.getPassword(),
                                                       memberDto.getCity(), memberDto.getStreet(), memberDto.getZipcode());
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

    public Page<MemberDto> memberFindAllPaging(Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 10, Sort.by("username").ascending());
        Page<MemberDto> memberDtoList = memberRepository.findByAllByDtoPaging(pageable);
        return memberDtoList;
    }
}
