package com.test.jpa.realTest.repository.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.test.jpa.realTest.dto.ItemDto;
import com.test.jpa.realTest.dto.QItemDto;
import com.test.jpa.realTest.entity.itemTable.Item;
import com.test.jpa.realTest.entity.itemTable.ItemDtype;
import com.test.jpa.realTest.entity.itemTable.QItem;

import javax.persistence.EntityManager;
import java.util.List;

import static com.test.jpa.realTest.entity.itemTable.QItem.item;

public class ItemRepositoryImpl implements ItemRepositoryQuery {

    private final EntityManager em;

    private final JPAQueryFactory queryFactory;

    public ItemRepositoryImpl(EntityManager em) {
        this.em = em;
        queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<ItemDto> findAllByDtypeList() {
        List<ItemDto> itemList = queryFactory
                .select(new QItemDto(item.id, item.name, item.price, item.stockQuantity, item.itemDtype,
                        item.itemDtype.when(ItemDtype.Book).then("도서")
                                .when(ItemDtype.Album).then("음악")
                                .when(ItemDtype.Movie).then("영화")
                                .otherwise("기타")
                ))
                .from(item)
                .fetch();

        return itemList;
    }
}
