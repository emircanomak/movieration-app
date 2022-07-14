package com.example.filmsitesi.product.controllers;

import com.example.filmsitesi.generic.response.RestResponse;
import com.example.filmsitesi.product.dtos.ProductCreateRequestDto;
import com.example.filmsitesi.product.dtos.ProductResponseDto;
import com.example.filmsitesi.product.dtos.ProductUpdateRequestDto;
import com.example.filmsitesi.product.services.abstracts.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/all")
    public ResponseEntity getAllProducts(){
        List<ProductResponseDto> productResponseDtoList = productService.getAllProducts();
        return ResponseEntity.ok(RestResponse.success(productResponseDtoList));
    }
    @GetMapping("/{id}")
    public ResponseEntity getProductById(@PathVariable Long id){
        ProductResponseDto productResponseDto = productService.getProductById(id);
        return ResponseEntity.ok(RestResponse.success(productResponseDto));
    }
    @PostMapping
    public ResponseEntity createProduct(@RequestBody ProductCreateRequestDto productCreateRequestDto){
        ProductResponseDto productResponseDto = productService.createProduct(productCreateRequestDto);
        return ResponseEntity.ok(RestResponse.success(productResponseDto));
    }
    @PutMapping("/{id}")
    public ResponseEntity updateProduct(@PathVariable Long id, @RequestBody ProductUpdateRequestDto productUpdateRequestDto){
        ProductResponseDto productResponseDto = productService.updateProduct(id, productUpdateRequestDto);
        return ResponseEntity.ok(RestResponse.success(productResponseDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteProductById(Long id){
        productService.deleteProductById(id);
        return ResponseEntity.ok(RestResponse.empty());
    }

}
