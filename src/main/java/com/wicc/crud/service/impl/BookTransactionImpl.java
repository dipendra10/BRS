package com.wicc.crud.service.impl;

import com.wicc.crud.Dto.BookTransactionDto;
import com.wicc.crud.entity.*;
import com.wicc.crud.enums.RentStatus;
import com.wicc.crud.repo.BookTransactionRepo;
import com.wicc.crud.service.BookTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookTransactionImpl implements BookTransactionService {
    @Autowired
    private BookTransactionRepo bookTransactionRepo;

    @Override
    public BookTransactionDto save(BookTransactionDto bookTransactionDto) {
        Book book = new Book();
        book.setId(bookTransactionDto.getBookDto());

        Member member = new Member();
        member.setId(bookTransactionDto.getMemberDto());

        BookTransaction entity = null;
        entity = BookTransaction.builder()
                .id(bookTransactionDto.getId())
                .code(bookTransactionDto.getCode())
                .fromDate(bookTransactionDto.getFromDate())
                .toDate(bookTransactionDto.getToDate())
                .rentTypes(RentStatus.valueOf(bookTransactionDto.getRentTypes()))
                .activeClosed(bookTransactionDto.getActiveClosed())
                .book(book)
                .member(member)
                .build();
        entity =bookTransactionRepo.save(entity);
        return BookTransactionDto.builder().id(entity.getId()).build();
    }

    @Override
    public List<BookTransactionDto> findAll() {
        List<BookTransaction> bookTransactions =bookTransactionRepo.findAll();
        return bookTransactions.stream().map(
                book -> BookTransactionDto.builder()
                        .id(book.getId())
                        .code(book.getCode())
                        .fromDate(book.getFromDate())
                        .toDate(book.getToDate())
                        .activeClosed(book.getActiveClosed())
                        .build()
        ).collect(Collectors.toList());
    }

    @Override
    public BookTransactionDto findById(Integer id) {
        BookTransaction book;
        Optional<BookTransaction> optionalBookTransaction = bookTransactionRepo.findById(id);
        if(optionalBookTransaction.isPresent())
        {
            book = optionalBookTransaction.get();
            return  BookTransactionDto.builder()
                    .id(book.getId())
                    .code(book.getCode())
                    .fromDate(book.getFromDate())
                    .toDate(book.getToDate())
                    .rentTypes(String.valueOf(book.getRentTypes()))
                    .activeClosed(book.getActiveClosed())
                    .bookDto(book.getBook().getId())
                    .memberDto(book.getMember().getId())
                    .build();
        }
        return null;
    }

    @Override
    public void deleteById(Integer id) {
      bookTransactionRepo.deleteById(id);
    }
}
