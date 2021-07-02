package com.test.jpa.realTest.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AddressTest {

    @Test
    public void 주소_등록() throws Exception{
        Address address = new Address("서울","천호","11111");

        assertThat(address.getCity()).isEqualTo("서울");
        assertThat(address.getStreet()).isEqualTo("천호");
        assertThat(address.getZipcode()).isEqualTo("11111");
    }
}