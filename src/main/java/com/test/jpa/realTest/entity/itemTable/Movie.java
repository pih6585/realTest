package com.test.jpa.realTest.entity.itemTable;

import com.test.jpa.realTest.entity.Item;
import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@DiscriminatorValue("Movie")
public class Movie extends Item {

    private String director;

    private String actor;

    public Movie() {
    }

    public Movie movieCreate(String director, String actor, Long id, String name, int price, int stockQuantity) {
        this.director = director;
        this.actor = actor;
        this.itemCreate(id, name, price, stockQuantity);
        return this;
    }
}
