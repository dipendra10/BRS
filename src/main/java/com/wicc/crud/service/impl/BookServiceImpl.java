package com.wicc.crud.service.impl;

import com.wicc.crud.Dto.BookDto;
import com.wicc.crud.Dto.ResponseDto;
import com.wicc.crud.components.FileStorageComponent;
import com.wicc.crud.entity.Author;
import com.wicc.crud.entity.Book;
import com.wicc.crud.entity.Category;
import com.wicc.crud.repo.BookRepo;
import com.wicc.crud.service.BookService;
import com.wicc.crud.service.GenericCrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
public class BookServiceImpl implements BookService, GenericCrudService<BookDto,Integer>{

    @Autowired
    private BookRepo bookRepo;
    private FileStorageComponent fileStorageComponent;

    public BookServiceImpl(FileStorageComponent fileStorageComponent) {
        this.fileStorageComponent = fileStorageComponent;
    }

    @Override
    public BookDto save(BookDto bookDto) throws IOException {

        List<Author> authorList = new ArrayList<>();
        bookDto.getAuthorDtoList().forEach(e->
        {
            Author author = new Author();
            author.setId(e);
            authorList.add(author);
        });

        Category category =new Category();
        category.setId(bookDto.getCategoryDto());

        Book entity = null;
        ResponseDto responseDto = fileStorageComponent.storeFile(bookDto.getMultipartFile());
        if(responseDto.isStatus()){
            entity = Book.builder()
                    .id(bookDto.getId())
                    .name(bookDto.getName())
                    .numberOfPages(bookDto.getNumberOfPages())
                    .isbnNumber(bookDto.getIsbnNumber())
                    .rating(bookDto.getRating())
                    .stockCount(bookDto.getStockCount())
                    .publishedDate(bookDto.getPublishedDate())
                    .filePath(responseDto.getMessage())
                    .authorList(authorList)
                    .category(category)
                    .build();
            entity = bookRepo.save(entity);
            return BookDto.builder().id(entity.getId()).build();
        }else{
            log.error(responseDto.getMessage());
            return null;
        }
    }

    @Override
    public List<BookDto> findAll() {
        List<Book> books =bookRepo.findAll();
        return books.stream().map(
                book -> BookDto.builder()
                        .id(book.getId())
                        .name(book.getName())
                        .numberOfPages(book.getNumberOfPages())
                        .isbnNumber(book.getIsbnNumber())
                        .rating(book.getRating())
                        .stockCount(book.getStockCount())
                        .publishedDate(book.getPublishedDate())
                        .build()
        ).collect(Collectors.toList());
    }

    @Override
    public BookDto findById(Integer integer) throws IOException {
        Book book;
        Optional<Book> optionalBook = bookRepo.findById(integer);
        if(optionalBook.isPresent())
        {
            book = optionalBook.get();
            return  BookDto.builder()
                    .id(book.getId())
                    .name(book.getName())
                    .numberOfPages(book.getNumberOfPages())
                    .isbnNumber(book.getIsbnNumber())
                    .rating(book.getRating())
                    .stockCount(book.getStockCount())
                    .publishedDate(book.getPublishedDate())
                    .filePath(book.getFilePath())
                    .authorDtoList(book.getAuthorList().stream().map(author -> author.getId()).collect(Collectors.toList()))
                    .categoryDto(book.getCategory().getId())
                    .build();
        }
        return null;
    }

    @Override
    public BookDto viewById(Integer integer) throws IOException {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {
        bookRepo.deleteById(integer);
    }
}

