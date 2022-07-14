package com.example.filmsitesi.category.services.concretes;

import com.example.filmsitesi.category.converters.CategoryMapper;
import com.example.filmsitesi.category.dtos.CategoryCreateRequestDto;
import com.example.filmsitesi.category.dtos.CategoryResponseDto;
import com.example.filmsitesi.category.dtos.CategoryUpdateRequestDto;
import com.example.filmsitesi.category.entities.Category;
import com.example.filmsitesi.category.enums.CategoryErrorMessage;
import com.example.filmsitesi.category.repositories.CategoryDao;
import com.example.filmsitesi.category.services.abstracts.CategoryService;
import com.example.filmsitesi.generic.exceptions.BusinessException;
import com.example.filmsitesi.generic.exceptions.GeneralErrorMessage;
import com.example.filmsitesi.generic.exceptions.ItemNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryDao categoryDao;

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        List<Category> categoryList = categoryDao.findAll();
        List<CategoryResponseDto> categoryResponseDtoList = CategoryMapper.
                                                                  INSTANCE.
                                                                  mapCategoryListToCategoryResponseDtoList(categoryList);
        return categoryResponseDtoList;
    }

    @Override
    public CategoryResponseDto getCategoryById(Long id) {
        Optional<Category> category =  categoryDao.findById(id);
        if(!category.isPresent()){
            throw new ItemNotFoundException(CategoryErrorMessage.CATEGORY_DOES_NOT_EXİST);
        }
        CategoryResponseDto categoryResponseDto = CategoryMapper.INSTANCE.mapCategoryToCategoryResponseDto(category.get());
        return categoryResponseDto;
    }

    @Override
    public CategoryResponseDto createCategory(CategoryCreateRequestDto categoryCreateRequestDto) {
        if(categoryCreateRequestDto == null){
            throw new BusinessException(GeneralErrorMessage.VALUES_CANNOT_BE_NULL);
        }
       Category category = CategoryMapper.INSTANCE.mapCategoryCreateRequestDtoToCategory(categoryCreateRequestDto);
       category = categoryDao.save(category);
       CategoryResponseDto categoryResponseDto = CategoryMapper.INSTANCE.mapCategoryToCategoryResponseDto(category);
       return categoryResponseDto;
    }

    @Override
    public CategoryResponseDto updateCategory(Long id, CategoryUpdateRequestDto categoryUpdateRequestDto) {
        if(!isExist(id)){
            throw new BusinessException(GeneralErrorMessage.VALUES_CANNOT_BE_NULL);
        }
        if(id != categoryUpdateRequestDto.getId()){
            throw new BusinessException(GeneralErrorMessage.ID_NOT_EQUAL);
        }
       Category category = CategoryMapper.INSTANCE.mapCategoryUpdateRequestDtoToCategory(categoryUpdateRequestDto);
       category = categoryDao.save(category);
       CategoryResponseDto categoryResponseDto = CategoryMapper.INSTANCE.mapCategoryToCategoryResponseDto(category);
        return categoryResponseDto;
    }

    @Override
    public void deleteCategoryById(Long id) {

        categoryDao.deleteById(id);
    }

    //TODO : these methods should be in a generic service!

    private boolean isExist(Long id){
        Optional<Category> categoryToFind = categoryDao.findById(id);
        if(!categoryToFind.isPresent()){
            return false;
        }
        return true;
    }

}
