package com.test.jpa.realTest.entity;

import com.test.jpa.realTest.enumClass.DeliveryStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;


    public void createOrder(Order order) {
        this.order = order;
    }

    public static Delivery createDelivery(Address address, DeliveryStatus status) {
       Delivery delivery = new Delivery(address,status);
        return delivery;
    }

    private Delivery(Address address, DeliveryStatus status){
        this.address = address;
        this.status = status;
    }
}
