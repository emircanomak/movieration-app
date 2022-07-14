package com.example.filmsitesi.product.converters;


import com.example.filmsitesi.product.dtos.ProductCreateRequestDto;
import com.example.filmsitesi.product.dtos.ProductResponseDto;
import com.example.filmsitesi.product.dtos.ProductUpdateRequestDto;
import com.example.filmsitesi.product.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source="categoryId", target="category.id")
    Product mapProductCreateRequestDtoToProduct(ProductCreateRequestDto productCreateRequestDto);
    @Mapping(source="category.id", target="categoryId")
    @Mapping(source="category.categoryName", target="categoryName")
    ProductResponseDto mapProductToProductResponseDto(Product product);

    @Mapping(source="categoryId", target="category.id")
    Product mapProductUpdateRequestDtoToProduct(ProductUpdateRequestDto productUpdateRequestDto);

    List<ProductResponseDto> mapProductListToProductResponseDtoList(List<Product> productList);
}
