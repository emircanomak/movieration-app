package com.example.filmsitesi.product.dtos;

import lombok.Data;

@Data
public class ProductCreateRequestDto {
    private String productName;
    private String productDesc;
    private String productUrl;
    private double productRate;
    private Long categoryId;
}
