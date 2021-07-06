package com.test.jpa.realTest.service;

import com.test.jpa.realTest.dto.AlbumDto;
import com.test.jpa.realTest.entity.itemTable.Album;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AlbumServiceTest {

    @Autowired
    AlbumService albumService;

    @Autowired
    EntityManager em;

    @Test
    public void 앨범_저장() throws Exception{
        AlbumDto albumDto1 = new AlbumDto("아이유","","금요일에만나요",800,10000);
        AlbumDto albumDto2 = new AlbumDto("BTS", "top1", "dyn", 12000, 1000);

        Long saveById1 = albumService.albumCreate(albumDto1);
        Long saveById2 = albumService.albumCreate(albumDto2);

        AlbumDto findAlbum1 = albumService.albumFindOne(saveById1);
        AlbumDto findAlbum2 = albumService.albumFindOne(saveById2);

        assertThat(findAlbum1.getArtist()).isEqualTo("아이유");
        assertThat(findAlbum1.getName()).isEqualTo("금요일에만나요");

        assertThat(findAlbum2.getPrice()).isEqualTo(12000);
        assertThat(findAlbum2.getStockQuantity()).isGreaterThanOrEqualTo(1000);
    }

    @Test
    public void 앨범_전체조회()throws Exception{
        AlbumDto albumDto1 = new AlbumDto("아이유","","금요일에만나요",800,10000);
        AlbumDto albumDto2 = new AlbumDto("BTS", "top1", "dyn", 12000, 1000);

        albumService.albumCreate(albumDto1);
        albumService.albumCreate(albumDto2);

        List<AlbumDto> albumList = albumService.albumFindAll();

        assertThat(albumList).extracting("artist").containsExactly("아이유","BTS");
        assertThat(albumList).extracting("price").containsExactly(800,12000);
    }

    @Test
    public void 앨범_수정() throws Exception{
        AlbumDto albumDto1 = new AlbumDto("아이유","","금요일에만나요",800,10000);

        Long saveById1 = albumService.albumCreate(albumDto1);

        AlbumDto findAlbum1 = albumService.albumFindOne(saveById1);

        findAlbum1.setName("1~2모음집");
        Long updateById1 = albumService.albumUpdate(findAlbum1);

        AlbumDto updateAlbum = albumService.albumFindOne(updateById1);

        assertThat(updateAlbum.getName()).isEqualTo("1~2모음집");
        assertThat(updateAlbum).isEqualTo(findAlbum1);
    }

}