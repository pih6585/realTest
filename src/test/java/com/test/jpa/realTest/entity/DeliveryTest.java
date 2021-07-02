package com.test.jpa.realTest.entity;

import com.test.jpa.realTest.enumClass.DeliveryStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DeliveryTest {

    @Test
    public void 배송_등록() throws Exception{
        Delivery delivery = new Delivery();
        Delivery delivery1 = delivery.createDelivery(1L, new Address("서울", "천호", "111-11"), DeliveryStatus.READY);

        assertThat(delivery1.getAddress().getCity()).isEqualTo("서울");
        assertThat(delivery1.getStatus()).isEqualTo(DeliveryStatus.READY);

    }
}