package com.test.jpa.realTest.entity;

import com.test.jpa.realTest.entity.itemTable.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryTest {

    @Test
    public void 자식_등록() throws Exception {
        Category category1 = new Category();
        category1.createChild(1L,"IT서적");

        Category category2 = new Category();
        category2.createChild(1L,"기타소설");

        assertThat(category1.getName()).isEqualTo("IT서적");
        assertThat(category2.getName()).isEqualTo("기타소설");

    }

    @Test
    public void 부모_등록() throws Exception{
        Category child1 = new Category();
        child1.createChild(1L,"IT서적");

        Category child2 = new Category();
        child2.createChild(1L,"기타소설");

        assertThat(child1.getName()).isEqualTo("IT서적");
        assertThat(child2.getName()).isEqualTo("기타소설");

        Category parent1 = new Category();
        parent1.createParent(3L,child1,"IT서적");

        Category parent2 = new Category();
        parent2.createParent(4L,child2,"C-D");

        assertThat(parent1.getParent().getId()).isEqualTo(1L);
        assertThat(parent2.getParent().getId()).isEqualTo(1L);

        boolean 등록유무 = child1.existCategoryId("IT서적");
        assertThat(등록유무).isTrue();
    }

    @Test
    public void 카테고리_아이템_등록() throws Exception {
        Category category1 = new Category();
        category1.createChild(1L,"IT서적");

        Category category2 = new Category();
        category2.createChild(1L,"기타소설");

        assertThat(category1.getName()).isEqualTo("IT서적");
        assertThat(category2.getName()).isEqualTo("기타소설");

        Book book1 = Book.bookCreate("김영한",null,"JPA",10000,100);
        book1.createCategory(category1);

        Book book2 = Book.bookCreate("김영한",null,"SPRING",10000,150);
        book2.createCategory(category1);

        List<Item> items = category1.getItems();
        assertThat(items).extracting("name").containsExactly("JPA","SPRING");

    }
}