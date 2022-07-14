package com.example.filmsitesi.category.services.abstracts;

import com.example.filmsitesi.category.dtos.CategoryCreateRequestDto;
import com.example.filmsitesi.category.dtos.CategoryResponseDto;
import com.example.filmsitesi.category.dtos.CategoryUpdateRequestDto;

import java.util.List;

public interface CategoryService {
    List<CategoryResponseDto> getAllCategories();

    CategoryResponseDto getCategoryById(Long id);

    CategoryResponseDto createCategory(CategoryCreateRequestDto categoryCreateRequestDto);

    CategoryResponseDto updateCategory(Long id, CategoryUpdateRequestDto categoryUpdateRequestDto);

    void deleteCategoryById(Long id);
}
