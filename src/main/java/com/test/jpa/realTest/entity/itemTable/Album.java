package com.test.jpa.realTest.entity.itemTable;

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
        Album album = new Album(null,artist,etc,name,price,stockQuantity);
        return album;
    }

    public static  Album albumUpdate(Long id, String artist, String etc, String name, int price, int stockQuantity) {
        Album album = new Album(id, artist,etc,name,price,stockQuantity);
        return album;
    }

    private Album(Long id, String artist, String etc, String name, int price, int stockQuantity){
        this.artist = artist;
        this.etc = etc;
        if(id != null){
            this.setId(id);
        }
        this.setName(name);
        this.setPrice(price);
        this.setStockQuantity(stockQuantity);
    }
}
