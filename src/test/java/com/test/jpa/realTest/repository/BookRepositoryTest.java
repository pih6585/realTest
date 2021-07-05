package com.test.jpa.realTest.repository;

import com.test.jpa.realTest.entity.itemTable.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BookRepositoryTest {

    @PersistenceContext
    EntityManager em;

    @Autowired  BookRepository bookRepository;

    @Test
    public void 도서_등록(){
        Book book1 = Book.bookCreate("김영한","11111232","JPA",10000,120);

        //Book book2 = Book.bookCreate("공지영", "123123322", "빗방울", 15000, 100);

        Book saveBook1 = bookRepository.save(book1);

        em.flush();
        em.clear();

        List<Book> list = bookRepository.findAll();

        assertThat(saveBook1.getAuthor()).isEqualTo("김영한");
        assertThat(list).extracting("name").containsExactly("JPA");
        System.out.println(saveBook1.getId());

        //Book saveBook2 = bookRepository.save(book2);

       /* assertThat(saveBook1.getAuthor()).isEqualTo(book1.getAuthor());
        assertThat(saveBook1.getName()).isEqualTo(book1.getName());
        assertThat(saveBook1.getPrice()).isEqualTo(book1.getPrice());

        assertThat(saveBook2.getAuthor()).isEqualTo(book2.getAuthor());
        assertThat(saveBook2.getName()).isEqualTo(book2.getName());
        assertThat(saveBook2.getPrice()).isEqualTo(book2.getPrice());*/
    }

}