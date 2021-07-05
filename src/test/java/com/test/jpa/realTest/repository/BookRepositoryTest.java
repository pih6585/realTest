package com.test.jpa.realTest.repository;

import com.test.jpa.realTest.entity.itemTable.Book;
import com.test.jpa.realTest.entity.itemTable.Item;
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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BookRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired  BookRepository bookRepository;

    @Test
    public void 도서_등록(){
        Book book1 = Book.bookCreate("김영한","11111232","JPA",10000,120);
        Book book2 = Book.bookCreate("공지영", "123123322", "빗방울", 15000, 100);

        Book saveBook1 = bookRepository.save(book1);
        Book saveBook2 = bookRepository.save(book2);

        em.flush();
        em.clear();

        Optional<Book> optBook1 = bookRepository.findById(saveBook1.getId());
        Book findBook1 = Optional.ofNullable(optBook1.get()).get();

        System.out.println("DTYPE ===============" + findBook1.getItemDtype());

        assertThat(saveBook1.getAuthor()).isEqualTo("김영한");
        assertThat(saveBook2.getAuthor()).isEqualTo("공지영");

        assertThat(saveBook1.getPrice()).isEqualTo(10000);
    }

}