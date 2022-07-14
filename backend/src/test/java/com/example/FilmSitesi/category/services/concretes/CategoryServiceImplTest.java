package com.example.filmsitesi.category.services.concretes;

import com.example.filmsitesi.category.dtos.CategoryCreateRequestDto;
import com.example.filmsitesi.category.dtos.CategoryResponseDto;
import com.example.filmsitesi.category.dtos.CategoryUpdateRequestDto;
import com.example.filmsitesi.category.entities.Category;
import com.example.filmsitesi.category.enums.CategoryErrorMessage;
import com.example.filmsitesi.category.repositories.CategoryDao;
import com.example.filmsitesi.generic.exceptions.BusinessException;
import com.example.filmsitesi.generic.exceptions.GeneralErrorMessage;
import com.example.filmsitesi.generic.exceptions.ItemNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @Mock
    private CategoryDao categoryDao;
    @InjectMocks
    private CategoryServiceImpl categoryServiceImpl;

    @Test
    void shouldGetAllCategoriesWhileAvailableTwoInstance() {
        Category category = Mockito.mock(Category.class);
        Category category2 = Mockito.mock(Category.class);
        List<Category> categoryList = categoryDao.findAll();
        categoryList.add(category);
        categoryList.add(category2);
        Mockito.when(categoryDao.findAll()).thenReturn(categoryList);
        List<CategoryResponseDto> categoryResponseDtoList = categoryServiceImpl.getAllCategories();
        assertEquals(2, categoryResponseDtoList.size());
    }
    @Test
    void shouldGetAllCategoriesAsEmptyList() {

        List<Category> categoryList = categoryDao.findAll();
        Mockito.when(categoryDao.findAll()).thenReturn(categoryList);
        List<CategoryResponseDto> categoryResponseDtoList = categoryServiceImpl.getAllCategories();
        assertEquals(0, categoryResponseDtoList.size());
    }
    @Test
    void shouldGetCategoryById() {
        Category category = Mockito.mock(Category.class);
        Mockito.when(category.getId()).thenReturn(1L);
        Mockito.when(categoryDao.findById(anyLong())).thenReturn(Optional.of(category));
        CategoryResponseDto categoryResponseDto = categoryServiceImpl.getCategoryById(1L);
        assertEquals(1L, categoryResponseDto.getId());
    }
    @Test
    void shouldNotGetCategoryByIdWhileItIsNotAvailable() {
        Mockito.when(categoryDao.findById(anyLong())).thenThrow(ItemNotFoundException.class);
        assertThrows(ItemNotFoundException.class, ()-> categoryServiceImpl.getCategoryById(anyLong()));
    }

    @Test
    void shouldCreateCategory() {
        CategoryCreateRequestDto categoryCreateRequestDto = Mockito.mock(CategoryCreateRequestDto.class);
        Category category = Mockito.mock(Category.class);
        Mockito.when(categoryDao.save(any())).thenReturn(category);
        Mockito.when(category.getId()).thenReturn(1L);
        CategoryResponseDto categoryResponseDto = categoryServiceImpl.createCategory(categoryCreateRequestDto);
        assertEquals(1L,categoryResponseDto.getId());
    }
    @Test
    void shouldNotCreateCategoryWhileParameterIsNull() {
      BusinessException businessException =   assertThrows(BusinessException.class, ()->categoryServiceImpl.createCategory(null));
        assertEquals(GeneralErrorMessage.VALUES_CANNOT_BE_NULL, businessException.getBaseErrorMessage());
    }



    @Test
    void shouldUpdateCategory() {
        CategoryUpdateRequestDto categoryUpdateRequestDto =  Mockito.mock(CategoryUpdateRequestDto.class);
        Mockito.when(categoryUpdateRequestDto.getId()).thenReturn(1L);

        Category category = Mockito.mock(Category.class);
        Mockito.when(categoryDao.findById(anyLong())).thenReturn(Optional.of(category));
        Mockito.when(category.getCategoryName()).thenReturn("Updated");


        Mockito.when(categoryDao.save(any())).thenReturn(category);
        CategoryResponseDto categoryResponseDto = categoryServiceImpl.updateCategory(1L, categoryUpdateRequestDto);
        assertEquals("Updated", categoryResponseDto.getCategoryName());
    }
    @Test
    void shouldNotUpdateCategoryIfGivenObjectIsNull() {

        BusinessException businessException = assertThrows(BusinessException.class,
                ()->categoryServiceImpl.updateCategory(anyLong(),null));
        assertEquals(GeneralErrorMessage.VALUES_CANNOT_BE_NULL,businessException.getBaseErrorMessage());

    }
    @Test
    void shouldNotUpdateCategoryIfIdDoesNotEqualGivenObjectId() {
        CategoryUpdateRequestDto categoryUpdateRequestDto = Mockito.mock(CategoryUpdateRequestDto.class);
        Mockito.when(categoryUpdateRequestDto.getId()).thenReturn(2L);

        Category category = Mockito.mock(Category.class);
        Mockito.when(categoryDao.findById(anyLong())).thenReturn(Optional.of(category));

        BusinessException businessException = assertThrows(
                                                            BusinessException.class,
                                                            ()->categoryServiceImpl.
                                                            updateCategory(1L,categoryUpdateRequestDto));
        assertEquals(GeneralErrorMessage.ID_NOT_EQUAL,businessException.getBaseErrorMessage());

    }

    @Test
    void shouldDeleteCategoryById() {
        Mockito.doNothing().when(categoryDao).deleteById(anyLong());
        categoryServiceImpl.deleteCategoryById(1L);
        Mockito.verify(categoryDao).deleteById(anyLong());
    }
    @Test
    void shouldNotDeleteCategoryById() {
        Mockito.doThrow(NoSuchElementException.class).when(categoryDao).deleteById(anyLong());
        assertThrows(NoSuchElementException.class,()->categoryServiceImpl.deleteCategoryById(1L));
        Mockito.verify(categoryDao).deleteById(anyLong());
    }
}