package com.example.filmsitesi.product.dtos;

import lombok.Data;

import java.util.List;

@Data
public class ProductUpdateRequestDto {
    private Long id;
    private String productName;
    private String productDesc;
    private String productUrl;
    private double productRate;
    private Long categoryId;
}
