package com.wicc.crud.service.impl;

import com.wicc.crud.Dto.CategoryDto;
import com.wicc.crud.entity.Category;
import com.wicc.crud.repo.CategoryRepo;
import com.wicc.crud.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto) {
        Category entity = new Category();
        entity.setId(categoryDto.getId());
        entity.setName(categoryDto.getName());
        entity.setDescription(categoryDto.getDescription());

        categoryRepo.save(entity);

        return CategoryDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }

    @Override
    public List<CategoryDto> findAll() {
        List<Category> categories=categoryRepo.findAll();
        return categories.stream().map(
                category -> CategoryDto.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .description(category.getDescription()).build()
        ).collect(Collectors.toList());
    }

    @Override
    public CategoryDto findById(Integer id) {
        Category category ;
        Optional<Category> optionalCategory = categoryRepo.findById(id);
        if(optionalCategory.isPresent())
        {
            category = optionalCategory.get();
            return  CategoryDto.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .description(category.getDescription())
                    .build();
        }
        return null;
    }

    @Override
    public void deleteCategoryById(Integer id) {
        categoryRepo.deleteById(id);
    }
}
