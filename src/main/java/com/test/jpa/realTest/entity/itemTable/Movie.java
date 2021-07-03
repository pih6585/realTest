package com.test.jpa.realTest.entity.itemTable;

import com.test.jpa.realTest.entity.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@DiscriminatorValue("Movie")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Movie extends Item {

    private String director;

    private String actor;


    public static Movie movieCreate(String director, String actor, String name, int price, int stockQuantity) {
        Movie movie = new Movie(director,actor,name,price,stockQuantity);
        return movie;
    }

    private Movie(String director, String actor, String name, int price, int stockQuantity){
        this.director = director;
        this.actor = actor;
        this.itemCreate( name, price, stockQuantity);
    }
}
