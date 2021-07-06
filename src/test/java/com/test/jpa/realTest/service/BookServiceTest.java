package com.test.jpa.realTest.service;

import com.test.jpa.realTest.dto.BookDto;
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
class BookServiceTest {

    @Autowired BookService bookService;

    @Autowired
    EntityManager em;

    @Test
    public void 도서_저장() throws Exception{
        BookDto bookDto1 = new BookDto("김영한","11111232","JPA",10000,120);
        Long saveById1 = bookService.bookCreate(bookDto1);

        BookDto bookDto2 = new BookDto("김영한","11111232","SPRING",10000,120);
        Long saveById2 = bookService.bookCreate(bookDto2);

        BookDto findBook1 = bookService.bookFindOne(saveById1);
        BookDto findBook2 = bookService.bookFindOne(saveById2);

        assertThat(findBook1.getAuthor()).isEqualTo(bookDto1.getAuthor());
        assertThat(findBook1.getName()).isEqualTo(bookDto1.getName());

        assertThat(findBook2.getAuthor()).isEqualTo(bookDto2.getAuthor());
        assertThat(findBook2.getName()).isEqualTo(bookDto2.getName());

        assertThat(findBook1.getPrice()).isEqualTo(bookDto1.getPrice());
        assertThat(findBook2.getPrice()).isEqualTo(bookDto2.getPrice());
    }

    @Test
    public void 도서_단일조회() throws Exception{
        BookDto bookDto1 = new BookDto("김영한","11111232","JPA",10000,120);
        Long saveById1 = bookService.bookCreate(bookDto1);

        BookDto bookDto2 = new BookDto("김영한","11111232","SPRING",10000,120);
        Long saveById2 = bookService.bookCreate(bookDto2);

        BookDto findBook1 = bookService.bookFindOne(saveById1);
        BookDto findBook2 = bookService.bookFindOne(saveById2);

        assertThat(findBook1.getAuthor()).isEqualTo(bookDto1.getAuthor());
        assertThat(findBook1.getName()).isEqualTo(bookDto1.getName());

        assertThat(findBook2.getAuthor()).isEqualTo(bookDto2.getAuthor());
        assertThat(findBook2.getName()).isEqualTo(bookDto2.getName());

        assertThat(findBook1.getPrice()).isEqualTo(bookDto1.getPrice());
        assertThat(findBook2.getPrice()).isEqualTo(bookDto2.getPrice());
    }

    @Test
    public void 도서_전체조회() throws Exception{
        BookDto bookDto1 = new BookDto("김영한","11111232","JPA",10000,120);
        Long saveById1 = bookService.bookCreate(bookDto1);

        BookDto bookDto2 = new BookDto("김영한","11111232","SPRING",10000,120);
        Long saveById2 = bookService.bookCreate(bookDto2);

        List<BookDto> bookDtoList = bookService.bookFindAll();

        assertThat(bookDtoList).extracting("name").containsExactly("JPA","SPRING");

    }

    @Test
    public void 도서_수정() throws  Exception{
        BookDto bookDto1 = new BookDto("김영한","11111232","JPA",10000,120);
        Long saveById1 = bookService.bookCreate(bookDto1);
        BookDto findBook1 = bookService.bookFindOne(saveById1);

        findBook1.setAuthor("Hello");

        Long upateBook1Id = bookService.bookUpdate(findBook1);

        em.flush();
        em.clear();

        BookDto updateFindBook1 = bookService.bookFindOne(upateBook1Id);

        assertThat(updateFindBook1.getAuthor()).isEqualTo("Hello");


    }

}