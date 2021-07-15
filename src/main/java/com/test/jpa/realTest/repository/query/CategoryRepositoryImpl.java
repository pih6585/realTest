package com.test.jpa.realTest.repository.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.test.jpa.realTest.dto.CategoryDto;
import com.test.jpa.realTest.dto.QCategoryDto;
import com.test.jpa.realTest.entity.QCategory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.test.jpa.realTest.entity.QCategory.category;

public class CategoryRepositoryImpl implements CategoryRepositoryQuery{

    private final EntityManager em;

    private final JPAQueryFactory queryFactory;

    public CategoryRepositoryImpl(EntityManager em) {
        this.em = em;
        queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<CategoryDto> findByParentExistList() {
        return queryFactory
                .select(new QCategoryDto(category.id, category.name,category.parent.id))
                .from(category)
                .where(category.parent.id.isNotNull())
                .fetch();
    }

    @Override
    public List<CategoryDto> findByParentList(List<Long> parentIds) {
        return queryFactory
                .select(new QCategoryDto(category.id, category.name,category.parent.id))
                .from(category)
                .where(category.id.notIn(parentIds))
                .fetch();
    }

}
