package com.wicc.crud.service.impl;

import com.wicc.crud.Dto.AuthorDto;
import com.wicc.crud.entity.Author;
import com.wicc.crud.repo.AuthorRepo;
import com.wicc.crud.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepo authorRepo;

    @Override
    public AuthorDto saveAuthor(AuthorDto authorDto) {
        Author entity = new Author();
        entity.setId(authorDto.getId());
        entity.setName(authorDto.getName());
        entity.setEmail(authorDto.getEmail());
        entity.setMobileNumber(authorDto.getMobileNumber());

        authorRepo.save(entity);

        return AuthorDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .mobileNumber(entity.getMobileNumber())
                .build();
    }

    @Override
    public List<AuthorDto> findAll() {
        List<Author> authors =authorRepo.findAll();
        return authors.stream().map(
                author -> AuthorDto.builder()
                        .id(author.getId())
                        .name(author.getName())
                        .email(author.getEmail())
                        .mobileNumber(author.getMobileNumber()).build()
        ).collect(Collectors.toList());
    }

    @Override
    public AuthorDto findById(Integer id) {
        Author author ;
        Optional<Author> optionalAuthor = authorRepo.findById(id);
        if(optionalAuthor.isPresent())
        {
            author = optionalAuthor.get();
            return  AuthorDto.builder()
                    .id(author.getId())
                    .name(author.getName())
                    .email(author.getEmail())
                    .mobileNumber(author.getMobileNumber())
                    .build();
        }
        return null;
    }

    @Override
    public void deleteAuthorById(Integer id) {
        authorRepo.deleteById(id);

    }
}
