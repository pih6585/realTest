package com.test.jpa.realTest.repository;

import com.test.jpa.realTest.entity.itemTable.Album;
import com.test.jpa.realTest.repository.query.AlbumRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long>, AlbumRepositoryQuery {

}
