package com.example.filmsitesi.product.services.abstracts;

import com.example.filmsitesi.product.dtos.ProductCreateRequestDto;
import com.example.filmsitesi.product.dtos.ProductResponseDto;
import com.example.filmsitesi.product.dtos.ProductUpdateRequestDto;

import java.util.List;

public interface ProductService {
    List<ProductResponseDto> getAllProducts();

    ProductResponseDto getProductById(Long id);

    ProductResponseDto createProduct(ProductCreateRequestDto productCreateRequestDto);

    ProductResponseDto updateProduct(Long id, ProductUpdateRequestDto productUpdateRequestDto);

    void deleteProductById(Long id);

}
