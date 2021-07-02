package com.test.jpa.realTest.entity;

import com.test.jpa.realTest.enumClass.DeliveryStatus;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    protected Delivery() {
    }

    public void createOrder(Order order){
        this.order = order;
    }

    public Delivery createDelivery(Long id, Address address, DeliveryStatus status) {
        this.id = id;
        this.address = address;
        this.status = status;
        return this;
    }
}
