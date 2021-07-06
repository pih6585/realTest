package com.test.jpa.realTest.repository;

import com.test.jpa.realTest.entity.itemTable.Album;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AlbumRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired AlbumRepository albumRepository;

    @Test
    public void 음악앨범_등록() throws Exception{
        Album album1 = Album.albumCreate("아이유",null,"금요일에만나요",800,10000);
        Album album2 = Album.albumCreate("BTS", "top1", "dyn", 12000, 1000);

        Album saveAlbum1 = albumRepository.save(album1);
        Album saveAlbum2 = albumRepository.save(album2);

        assertThat(saveAlbum1.getArtist()).isEqualTo("아이유");
        assertThat(saveAlbum1.getEtc()).isEqualTo(null);
        assertThat(saveAlbum1.getName()).isEqualTo("금요일에만나요");

        assertThat(saveAlbum2.getPrice()).isEqualTo(12000);
        assertThat(saveAlbum2.getStockQuantity()).isEqualTo(1000);

    }

    @Test
    public void 음악앨범_단일조회() throws Exception{
        Album album1 = Album.albumCreate("아이유",null,"금요일에만나요",800,10000);
        Album album2 = Album.albumCreate("BTS", "top1", "dyn", 12000, 1000);

        Album saveAlbum1 = albumRepository.save(album1);
        Album saveAlbum2 = albumRepository.save(album2);

        Optional<Album> optAlbum1 = albumRepository.findById(saveAlbum1.getId());
        Album findAlbum1 = Optional.ofNullable(optAlbum1.get()).get();

        Optional<Album> optAlbum2 = albumRepository.findById(saveAlbum2.getId());
        Album findAlbum2 = Optional.ofNullable(optAlbum2.get()).get();

        assertThat(findAlbum1).isEqualTo(saveAlbum1);
        assertThat(findAlbum1.getName()).isEqualTo(saveAlbum1.getName());

        assertThat(findAlbum2).isEqualTo(saveAlbum2);
        assertThat(findAlbum2.getName()).isEqualTo(saveAlbum2.getName());
    }

    @Test
    public void 음악앨범_전체조회() throws Exception{
        Album album1 = Album.albumCreate("아이유","","금요일에만나요",800,10000);
        Album album2 = Album.albumCreate("BTS", "top1", "dyn", 12000, 1000);

        Album saveAlbum1 = albumRepository.save(album1);
        Album saveAlbum2 = albumRepository.save(album2);

        em.flush();
        em.clear();

        List<Album> albumList = albumRepository.findAll();

        assertThat(albumList).extracting("artist").containsExactly("아이유","BTS");
        //assertThat(albumList).extracting("etc").isNotEmpty().containsExactly("top1");

    }
}