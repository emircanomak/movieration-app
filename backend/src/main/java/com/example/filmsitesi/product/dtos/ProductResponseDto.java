package com.example.filmsitesi.product.dtos;


import lombok.Data;

@Data
public class ProductResponseDto {

    private Long id;
    private String productName;
    private String productDesc;
    private String productUrl;
    private double productRate;
    private Long categoryId;
    private String categoryName;
}
