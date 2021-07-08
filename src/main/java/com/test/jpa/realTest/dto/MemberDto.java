package com.test.jpa.realTest.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

@Data
public class MemberDto {

    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    @Column(unique = true)
    private String email;

    @NotEmpty
    private String password;

    private String city;

    private String street;

    private String zipcode;

    public MemberDto() {
    }

    @QueryProjection
    public MemberDto(String username, String email,String pswd, String city, String street, String zipcode) {
        this.name = username;
        this.email = email;
        this.password = pswd;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    @QueryProjection
    public MemberDto(Long id, String username,String email, String pswd,  String city, String street, String zipcode) {
        this.id = id;
        this.name = username;
        this.email = email;
        this.password = pswd;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
