package com.test.jpa.realTest.service;

import com.test.jpa.realTest.dto.ItemDto;
import com.test.jpa.realTest.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    //전체 제품조회
    public List<ItemDto> itemList(){
        return itemRepository.findAllByDtypeList();
    }
}
