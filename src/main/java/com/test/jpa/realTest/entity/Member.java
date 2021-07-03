package com.test.jpa.realTest.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
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

    /**
     * protected level에서 static을 사용할건지.. 아님 Setter를 없애고 별도의 다시 custom method를 만들건지.. 고민필요
     * 이게 entity가 지금 테스트라 몇개 없지만 실 DB entity기준으로 지금까지 플젝으로는...1~200개씩나오는데 무분별하게 사용하지않으면
     * 문제가 될것 같지는 않음..
     */

    public static Member createMember(String name, String city, String street, String zipcode) {
        Member member = new Member(name, city, street, zipcode);
        return member;
    }

    public static Member updateMember(Long id,String name, String city, String street, String zipcode) {
        Member member = new Member(id, name, city, street, zipcode);
        return member;
    }

    private Member(String name, String city, String street, String zipcode) {
        this.username = name;
        Address address = new Address(city, street, zipcode);
        this.address = address;
    }

    private Member(Long id,String name, String city, String street, String zipcode) {
        this.id = id;
        this.username = name;
        Address address = new Address(city, street, zipcode);
        this.address = address;
    }

}
