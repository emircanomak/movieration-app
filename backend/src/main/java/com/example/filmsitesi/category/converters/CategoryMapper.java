package com.example.filmsitesi.category.converters;

import com.example.filmsitesi.category.dtos.CategoryCreateRequestDto;
import com.example.filmsitesi.category.dtos.CategoryResponseDto;
import com.example.filmsitesi.category.dtos.CategoryUpdateRequestDto;
import com.example.filmsitesi.category.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
    CategoryResponseDto mapCategoryToCategoryResponseDto(Category category);
    List<CategoryResponseDto> mapCategoryListToCategoryResponseDtoList(List<Category> categoryList);
    Category mapCategoryCreateRequestDtoToCategory(CategoryCreateRequestDto categoryCreateRequestDto);
    @Mapping(source="id", target="id")
    Category mapCategoryUpdateRequestDtoToCategory(CategoryUpdateRequestDto categoryUpdateRequestDto);
}
