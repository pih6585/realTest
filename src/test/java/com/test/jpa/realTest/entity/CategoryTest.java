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
        Category category1 = Category.categoryCreate("IT서적",null);

        Category category2 = Category.categoryCreate("기타소설",null);


        assertThat(category1.getName()).isEqualTo("IT서적");
        assertThat(category2.getName()).isEqualTo("기타소설");

    }

    @Test
    public void 부모_등록() throws Exception{
        Category child1 =  Category.categoryCreate("IT서적",null);
        Category child2 = Category.categoryCreate("기타소설",null);

        assertThat(child1.getName()).isEqualTo("IT서적");
        assertThat(child2.getName()).isEqualTo("기타소설");

        Category parent1 =  Category.categoryCreate("IT서적",child1);
        Category parent2 =  Category.categoryCreate("C-D",child2);

        assertThat(parent1.getParent().getName()).isEqualTo("IT서적");
        assertThat(parent2.getParent().getName()).isEqualTo("기타소설");

        boolean 등록유무 = child1.existCategoryId("IT서적");
        assertThat(등록유무).isTrue();
    }

    @Test
    public void 카테고리_아이템_등록() throws Exception {
        Category category1 =  Category.categoryCreate("IT서적",null);

        Book book1 = Book.bookCreate("김영한",null,"JPA",10000,100);
        CategoryItem categoryItem = CategoryItem.createCategoryItem(category1,book1);

        Category category2 =  Category.categoryCreate("기타소설",null);

        Book book2 = Book.bookCreate("김영한",null,"SPRING",10000,150);

        assertThat(category1.getName()).isEqualTo("IT서적");
        assertThat(category2.getName()).isEqualTo("기타소설");

        CategoryItem categoryItem2 = CategoryItem.createCategoryItem(category2,book2);


        assertThat(categoryItem2.getItem().getName()).isEqualTo("SPRING");


    }
}