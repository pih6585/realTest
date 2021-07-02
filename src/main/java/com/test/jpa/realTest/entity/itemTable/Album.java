package com.test.jpa.realTest.entity.itemTable;

import com.test.jpa.realTest.entity.Item;
import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@DiscriminatorValue("Album")
public class Album extends Item {

    private String artist;

    private String etc;

    public Album() {

    }

    public Album albumCreate(String artist, String etc, Long id, String name, int price, int stockQuantity) {
        this.artist = artist;
        this.etc = etc;
        this.itemCreate(id, name, price, stockQuantity);
        return this;
    }
}
