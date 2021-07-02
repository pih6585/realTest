package com.test.jpa.realTest.repository;

import com.test.jpa.realTest.dto.MemberDto;
import com.test.jpa.realTest.entity.Address;
import com.test.jpa.realTest.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional(readOnly = true)
@Rollback(value = false)
class MemberRepositoryTest {

    @Autowired
    EntityManager em;
    @Autowired MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        
    }

    @Transactional
    @Test
    public void 멤버_저장(){
        Member member = Member.createMember("member1","서울","천호","11-11");
        Member saveMember = memberRepository.save(member);
        assertThat(saveMember.getUsername()).isEqualTo("member1");
    }

    @Test
    @Transactional
    public void 맴버_단일조회(){
        Member member = Member.createMember("member1","서울","천호","11-11");
        Member saveMember = memberRepository.save(member);

        em.flush();
        em.clear();

        Optional<Member> optMember = memberRepository.findById(saveMember.getId());

        final Member findMember = Optional.ofNullable(optMember.get()).get();

        //assertThat(findMember).isEqualTo(saveMember);
        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    @Transactional
    public void 맴버_전체조회(){
        Member member1 = Member.createMember("member1","서울","천호","11-11");
        memberRepository.save(member1);

        Member member2 = Member.createMember("member2","서울","천호","11-11");
        Member resultMember2 =  member2.createMember("member2","서울","천호","11-11");
        memberRepository.save(resultMember2);

        List<Member> memberList = memberRepository.findAll();

        assertThat(memberList).extracting("username").containsExactly("member1","member2");
    }
}