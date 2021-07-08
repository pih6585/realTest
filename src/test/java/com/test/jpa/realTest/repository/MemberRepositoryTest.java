package com.test.jpa.realTest.repository;

import com.test.jpa.realTest.dto.MemberDto;
import com.test.jpa.realTest.entity.Address;
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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberRepositoryTest {

    @Autowired
    EntityManager em;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 멤버_저장() {
        Member member = Member.createMember("member1","pih6585@nate.com" ,"1234", "서울", "천호", "11-11");
        Member saveMember = memberRepository.save(member);
        assertThat(saveMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void 맴버_수정() {
        Member member = Member.createMember("member1", "pih6585@nate.com" ,"1234","서울", "천호", "11-11");
        Member saveMember = memberRepository.save(member);
        assertThat(saveMember.getUsername()).isEqualTo("member1");

        Optional<Member> optMember = memberRepository.findById(saveMember.getId());
        Member findMember = Optional.ofNullable(optMember.get()).get();
        findMember = Member.updateMember(saveMember.getId(), "testMember1",member.getEmail(),member.getPassword(),
                                         member.getAddress().getCity(), member.getAddress().getStreet(), member.getAddress().getZipcode());
        Member updateMember = memberRepository.save(findMember);

        assertThat(updateMember.getUsername()).isEqualTo("testMember1");
    }

    @Test
    public void 맴버_단일조회() {
        Member member = Member.createMember("member1","pih6585@nate.com" , "1234","서울", "천호", "11-11");
        Member saveMember = memberRepository.save(member);

        em.flush();
        em.clear();

        Optional<Member> optMember = memberRepository.findById(saveMember.getId());

        final Member findMember = Optional.ofNullable(optMember.get()).get();

        //assertThat(findMember).isEqualTo(saveMember);
        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void 맴버_전체조회() {
        Member member1 = Member.createMember("member1", "pih6585@nate.com" ,"1234","서울", "천호", "11-11");
        memberRepository.save(member1);

        Member member2 = Member.createMember("member2", "pih1111@nate.com" ,"1234","서울", "천호", "11-11");
        memberRepository.save(member2);

        List<Member> memberList = memberRepository.findAll();

        assertThat(memberList).extracting("username").containsExactly("member1", "member2");
    }

    @Test
    public void 맴버_페이징조회() throws Exception{
        for(int i=0;i<100;i++){
            Member member = Member.createMember("member"+(i+1),"pih"+i+"@nate.com" ,"1234","서울","천호",i+1+"");
            memberRepository.save(member);
        }
            Pageable pageable =PageRequest.of(0,10,Sort.by("username").ascending());
            Page<MemberDto> pageMemberList = memberRepository.findByAllByDtoPaging(pageable);

            for (MemberDto memberDto : pageMemberList) {
                System.out.println(memberDto.getName());
            }
            assertThat(pageMemberList.getSize()).isEqualTo(10);

    }

    @Test
    public void 이메일_회원정보조회() throws Exception{
        Member member = Member.createMember("member","pih1111@nate.com" ,"1234","서울","천호","111");
        memberRepository.save(member);

        Optional<Member> optMember = memberRepository.findByEmail("pih1111@nate.com");
        Member findMember = Optional.ofNullable(optMember.get()).get();

        assertThat(findMember.getEmail()).isEqualTo(member.getEmail());
    };

}