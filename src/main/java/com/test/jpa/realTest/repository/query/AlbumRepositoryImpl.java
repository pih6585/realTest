package com.test.jpa.realTest.repository.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.test.jpa.realTest.dto.AlbumDto;
import com.test.jpa.realTest.dto.BookDto;
import com.test.jpa.realTest.dto.QAlbumDto;
import com.test.jpa.realTest.dto.QBookDto;
import com.test.jpa.realTest.entity.itemTable.QAlbum;

import javax.persistence.EntityManager;
import java.util.List;

import static com.test.jpa.realTest.entity.itemTable.QAlbum.album;
import static com.test.jpa.realTest.entity.itemTable.QBook.book;

public class AlbumRepositoryImpl implements AlbumRepositoryQuery {

    private final EntityManager em;

    private final JPAQueryFactory queryFactory;

    public AlbumRepositoryImpl(EntityManager em) {
        this.em = em;
        queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public AlbumDto findByIdByDto(Long id) {
        AlbumDto albumDto = queryFactory
                .select(new QAlbumDto(album.id,album.artist,album.etc,album.name,album.price,album.stockQuantity,album.itemDtype))
                .from(album)
                .where(album.id.eq(id))
                .fetchOne();
        return albumDto;
    }

    @Override
    public List<AlbumDto> findByAllByDto() {
        List<AlbumDto> albumList = queryFactory
                .select(new QAlbumDto(album.id, album.artist, album.etc, album.name, album.price, album.stockQuantity, album.itemDtype))
                .from(album)
                .fetch();
        return albumList;
    }
}
