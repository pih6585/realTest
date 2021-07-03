package com.test.jpa.realTest.entity.itemTable;

import com.test.jpa.realTest.entity.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@DiscriminatorValue("Album")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Album extends Item {

    private String artist;

    private String etc;


    public static  Album albumCreate(String artist, String etc, String name, int price, int stockQuantity) {
        Album album = new Album(artist,etc,name,price,stockQuantity);
        return album;
    }

    private Album(String artist, String etc, String name, int price, int stockQuantity){
        this.artist = artist;
        this.etc = etc;
        this.itemCreate(name, price, stockQuantity);
    }
}
