package com.test.jpa.realTest.service;

import com.test.jpa.realTest.dto.MemberDto;
import com.test.jpa.realTest.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberServiceTest {

    @Autowired MemberService memberService;

    @Autowired
    EntityManager em;


    @Test
    public void 맴버_서비스저장() throws Exception{
        MemberDto memberDto1 = new MemberDto("member1","서울","천호","111-11");
        Long memberId1 = memberService.memberCreate(memberDto1);

        MemberDto memberDto2 = new MemberDto("member2","강남","청담","121-11");
        Long memberId2  = memberService.memberCreate(memberDto2);

        MemberDto findMember1 = memberService.memberFindOne(memberId1);
        MemberDto findMember2 = memberService.memberFindOne(memberId2);

        assertThat(findMember1.getName()).isEqualTo(memberDto1.getName());
        assertThat(findMember1).isEqualTo(memberDto1);

        assertThat(findMember2.getName()).isEqualTo(memberDto2.getName());
        assertThat(findMember2).isEqualTo(memberDto2);
    }


    @Test
    public void 맴버_서비스수정() {
        MemberDto memberDto1 = new MemberDto("member1","서울","천호","111-11");
        Long memberId1 =  memberService.memberCreate(memberDto1);
        memberDto1.setId(memberId1);

        MemberDto memberDto2 = new MemberDto("member2","강남","청담","121-11");
        Long memberId2 = memberService.memberCreate(memberDto2);
        memberDto2.setId(memberId2);

        MemberDto findMember1 = memberService.memberFindOne(memberId1);
        MemberDto findMember2 = memberService.memberFindOne(memberId2);

        assertThat(findMember1.getName()).isEqualTo(memberDto1.getName());
        assertThat(findMember1).isEqualTo(memberDto1);

        assertThat(findMember2.getName()).isEqualTo(memberDto2.getName());
        assertThat(findMember2).isEqualTo(memberDto2);

        findMember1.setName("testMember");
        Long updateId = memberService.memberUpdate(findMember1);
        MemberDto updateDto = memberService.memberFindOne(updateId);

        assertThat(updateDto.getName()).isEqualTo("testMember");
    }

    @Test
    public void 맴버_서비스단일조회() {
        MemberDto memberDto1 = new MemberDto("member1","서울","천호","111-11");
        Long memberId1 = memberService.memberCreate(memberDto1);

        MemberDto memberDto2 = new MemberDto("member2","강남","청담","121-11");
        Long memberId2  = memberService.memberCreate(memberDto2);

        MemberDto findMember1 = memberService.memberFindOne(memberId1);

        assertThat(findMember1.getName()).isEqualTo(memberDto1.getName());

    }

    @Test
    public void 맴버_서비스전채조회() {
        MemberDto memberDto1 = new MemberDto("member1","서울","천호","111-11");
        Long memberId1 = memberService.memberCreate(memberDto1);

        MemberDto memberDto2 = new MemberDto("member2","강남","청담","121-11");
        Long memberId2  = memberService.memberCreate(memberDto2);

        List<MemberDto> memberList = memberService.memberFindAll();

        for (MemberDto memberDto : memberList) {
            System.out.println(memberDto.toString());
        }
        assertThat(memberList).extracting("username").containsExactly("member1","member2");
    }

    @Test
    public void 맴버_서비스_페이징조회() {
        for(int i=0;i<100;i++){
            MemberDto memberDto = new MemberDto("member"+(i+1),"서울","천호",i+1+"");
            memberService.memberCreate(memberDto);
        }
        Pageable pageable = PageRequest.of(0,10);
        Page<MemberDto> memberDtoPagingList = memberService.memberFindAllPaging(pageable);

        for (MemberDto memberDto : memberDtoPagingList) {
            System.out.println(memberDto.getName());
        }
        assertThat(memberDtoPagingList.getSize()).isEqualTo(10);
    }

}