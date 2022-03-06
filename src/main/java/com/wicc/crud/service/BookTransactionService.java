package com.wicc.crud.service;

import com.wicc.crud.Dto.BookTransactionDto;

import java.util.List;

public interface BookTransactionService {
    BookTransactionDto save(BookTransactionDto bookTransactionDto);

    List<BookTransactionDto> findAll();

    BookTransactionDto findById(Integer id);

    void deleteById(Integer id);
}
