package com.test.jpa.realTest.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class MemberDto {

    private Long id;

    @NotEmpty
    private String name;

    private String city;

    private String street;

    private String zipcode;

    public MemberDto() {
    }

    @QueryProjection
    public MemberDto(String username, String city, String street, String zipcode) {
        this.name = username;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    @QueryProjection
    public MemberDto(Long id, String username, String city, String street, String zipcode) {
        this.id = id;
        this.name = username;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
