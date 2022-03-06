package com.wicc.crud.service;

import com.wicc.crud.Dto.AuthorDto;

import java.util.List;

public interface AuthorService {
    AuthorDto saveAuthor(AuthorDto authorDto);
    List<AuthorDto> findAll();

    AuthorDto findById(Integer id);

    void deleteAuthorById(Integer id);
}
