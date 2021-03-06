package com.test.jpa.realTest.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberTest {


    @Test
    public void 맴버_등록() throws Exception {
        Member createMember = Member.createMember("member1","pih6585@nate.com" ,"1234","서울", "천호동", "123-12");

        assertThat(createMember.getAddress().getCity()).isEqualTo("서울");
        assertThat(createMember.getAddress().getStreet()).isEqualTo("천호동");
        assertThat(createMember.getAddress().getZipcode()).isEqualTo("123-12");
        assertThat(createMember.getUsername()).isEqualTo("member1");

    }

    @Test
    public void 맴버_등록V2() throws Exception {
        Member updateMember = Member.updateMember( 1L,"member1","pih6585@nate.com" ,"1234,","서울", "천호동", "123-12");

        assertThat(updateMember.getId()).isEqualTo(1L);
        assertThat(updateMember.getAddress().getCity()).isEqualTo("서울");
        assertThat(updateMember.getAddress().getStreet()).isEqualTo("천호동");
        assertThat(updateMember.getAddress().getZipcode()).isEqualTo("123-12");
        assertThat(updateMember.getUsername()).isEqualTo("member1");

    }

}