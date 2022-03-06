package com.wicc.crud.service;

import com.wicc.crud.Dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto saveCategory(CategoryDto categoryDto);
    List<CategoryDto> findAll();

    CategoryDto findById(Integer id);

    void deleteCategoryById(Integer id);
}
