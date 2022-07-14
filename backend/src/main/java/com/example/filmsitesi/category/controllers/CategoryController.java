package com.example.filmsitesi.category.controllers;

import com.example.filmsitesi.category.dtos.CategoryCreateRequestDto;
import com.example.filmsitesi.category.dtos.CategoryResponseDto;
import com.example.filmsitesi.category.dtos.CategoryUpdateRequestDto;
import com.example.filmsitesi.category.services.abstracts.CategoryService;
import com.example.filmsitesi.generic.response.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity getAllCategories() {
        List<CategoryResponseDto> categoryResponseDtoList = categoryService.getAllCategories();
        return ResponseEntity.ok(RestResponse.success(categoryResponseDtoList));
    }
    @GetMapping("/{id}")
    public ResponseEntity getCategoryById(@PathVariable Long id){
        CategoryResponseDto categoryResponseDto = categoryService.getCategoryById(id);
        return ResponseEntity.ok(RestResponse.success(categoryResponseDto));
    }
    @PostMapping
    public ResponseEntity createCategory(@RequestBody CategoryCreateRequestDto categoryCreateRequestDto) {
       CategoryResponseDto categoryResponseDto =  categoryService.createCategory(categoryCreateRequestDto);
        return ResponseEntity.ok(RestResponse.success(categoryResponseDto));
    }
    @PutMapping("/{id}")
    public ResponseEntity updateCategory(@PathVariable Long id,
                                         @RequestBody CategoryUpdateRequestDto categoryUpdateRequestDto)
    {
        CategoryResponseDto categoryResponseDto = categoryService.updateCategory(id, categoryUpdateRequestDto);
        return ResponseEntity.ok(RestResponse.success(categoryResponseDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.ok(RestResponse.empty());
    }

}
