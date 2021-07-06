package com.test.jpa.realTest.repository;

import com.test.jpa.realTest.entity.itemTable.Item;
import com.test.jpa.realTest.repository.query.ItemRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemRepositoryQuery {

}
