package com.test.jpa.realTest.service;

import com.test.jpa.realTest.dto.BookDto;
import com.test.jpa.realTest.entity.itemTable.Book;
import com.test.jpa.realTest.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookService {

    @Autowired
    BookRepository bookRepository;

    //도서_저장
    public Long bookCreate(BookDto bookDto){
        Book book = Book.bookCreate(bookDto.getAuthor(),bookDto.getIsbn(),bookDto.getName(),bookDto.getPrice(),bookDto.getStockQuantity());
        Book saveBook = bookRepository.save(book);
        return saveBook.getId();
    }

    //도서_단일조회
    public BookDto bookFindOne(Long id){
        BookDto bookDto = bookRepository.findByOneByDto(id);
        return bookDto;
    }

    //도서_전체조회
    public List<BookDto> bookFindAll(){
        List<BookDto> booktDtoList = bookRepository.findByAllByDto();
        return booktDtoList;
    }


}
