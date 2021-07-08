package com.test.jpa.realTest.service;

import com.test.jpa.realTest.dto.MemberDto;
import com.test.jpa.realTest.entity.Member;
import com.test.jpa.realTest.repository.MemberRepository;
import jdk.internal.dynalink.support.NameCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MemberService implements UserDetailsService {

    @Autowired
    MemberRepository memberRepository;

    @Transactional
    public Long memberCreate(MemberDto memberDto){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String epw = passwordEncoder.encode(memberDto.getPassword());
        Member member = Member.createMember(memberDto.getName(), memberDto.getEmail(), epw,
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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Member> optMember = memberRepository.findByEmail(email);
        Member findMember = Optional.ofNullable(optMember.get()).get();
        if(findMember == null){
            throw new UsernameNotFoundException("UsernameNotFoundException");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_MEMBER"));
        return  new User(findMember.getEmail(), findMember.getPassword(), authorities);
    }
}
