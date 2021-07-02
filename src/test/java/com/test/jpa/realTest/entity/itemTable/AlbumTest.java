package com.test.jpa.realTest.entity.itemTable;

import com.test.jpa.realTest.entity.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AlbumTest {

    @Test
    public void 앨범_등록() throws Exception{
        Album album = new Album();
        Album createAlbum = album.albumCreate("김영한", "JPA TEST", 1L, "JPA", 10000, 150);

        assertThat(createAlbum.getName()).isEqualTo("JPA");
        assertThat(createAlbum.getArtist()).isEqualTo("김영한");
        assertThat(createAlbum.getPrice()).isEqualTo(10000);
        assertThat(createAlbum.getStockQuantity()).isEqualTo(150);
    }

}