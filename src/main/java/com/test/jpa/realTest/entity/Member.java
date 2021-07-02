package com.test.jpa.realTest.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String username;

    @Embedded
    private Address address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    private List<Order> orderList = new ArrayList<>();


    public static Member createMember(String name, String city, String street, String zipcode) {
        Member member = new Member();
        member.setUsername(name);
        Address address = new Address(city,street,zipcode);
        member.setAddress(address);
        return member;
    }

    public Member createTest(Long id, String username, Address address) {
        this.id = id;
        this.username = username;
        this.address = address;
        return this;
    }

}
