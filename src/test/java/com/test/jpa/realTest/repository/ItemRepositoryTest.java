package com.test.jpa.realTest.repository;

import com.test.jpa.realTest.dto.ItemDto;
import com.test.jpa.realTest.entity.itemTable.Book;
import com.test.jpa.realTest.entity.itemTable.Movie;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    EntityManager em;

    @Test
    public void 전체_아이템_리스트() throws Exception{
        Book book1 = Book.bookCreate("김영한","11111","JPA",10000,100);
        Book book2 = Book.bookCreate("토비","11111","Spring",30000,200);

        em.persist(book1);
        em.persist(book2);

        Movie movie  = Movie.movieCreate("류승범","류승룡","7번가",10000,100);
        em.persist(movie);

        em.flush();
        em.clear();

        List<ItemDto> itemDtoList = itemRepository.findAllByDtypeList();

        for (ItemDto itemDto : itemDtoList) {
            System.out.println(itemDto.getItemDtypeName());
        }

        assertThat(itemDtoList).extracting("name").containsExactly("JPA","Spring","7번가");

    }
}