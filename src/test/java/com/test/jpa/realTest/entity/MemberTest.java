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
    public void 전체자료_테스트() throws  Exception{
        Member member = new Member();
        Address address = AddressInit("서울","천호동","123-12");
        Member createMember = member.createTest(1L, "member1", address);

        assertThat(createMember.getAddress().getCity()).isEqualTo("서울");
        assertThat(createMember.getAddress().getStreet()).isEqualTo("천호동");
        assertThat(createMember.getAddress().getZipcode()).isEqualTo("123-12");
        assertThat(createMember.getUsername()).isEqualTo("member1");

    }

    private Address AddressInit(String city, String street, String zipcode) {
        Address address = new Address(city,street,zipcode);
        return address;
    }
}