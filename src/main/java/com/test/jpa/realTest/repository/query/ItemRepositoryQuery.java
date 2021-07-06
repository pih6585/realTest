package com.test.jpa.realTest.repository.query;

import com.test.jpa.realTest.dto.ItemDto;
import com.test.jpa.realTest.entity.itemTable.Item;

import java.util.List;

public interface ItemRepositoryQuery {
    List<ItemDto> findAllByDtypeList();
}
