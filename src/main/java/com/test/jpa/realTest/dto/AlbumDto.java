package com.test.jpa.realTest.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.test.jpa.realTest.entity.itemTable.ItemDtype;
import lombok.Data;

@Data
public class AlbumDto extends ItemDto {

    private String artist;

    private String etc;

    public AlbumDto(){

    }

    public AlbumDto(String artist, String etc, String name, int price, int stockQuantity) {
        this.artist = artist;
        this.etc = etc;
        this.setName(name);
        this.setPrice(price);
        this.setStockQuantity(stockQuantity);
    }

    @QueryProjection
    public AlbumDto(Long id, String artist, String etc, String name, int price, int stockQuantity, ItemDtype itemDtype) {
        this.setId(id);
        this.artist = artist;
        this.etc = etc;
        this.setName(name);
        this.setPrice(price);
        this.setStockQuantity(stockQuantity);
        this.setItemDtype(itemDtype);
    }

}
