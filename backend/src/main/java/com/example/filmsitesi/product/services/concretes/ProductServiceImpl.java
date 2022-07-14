package com.example.filmsitesi.product.services.concretes;

import com.example.filmsitesi.generic.exceptions.BusinessException;
import com.example.filmsitesi.generic.exceptions.GeneralErrorMessage;
import com.example.filmsitesi.generic.exceptions.ItemNotFoundException;
import com.example.filmsitesi.product.converters.ProductMapper;
import com.example.filmsitesi.product.dtos.ProductCreateRequestDto;
import com.example.filmsitesi.product.dtos.ProductResponseDto;
import com.example.filmsitesi.product.dtos.ProductUpdateRequestDto;
import com.example.filmsitesi.product.entities.Product;
import com.example.filmsitesi.product.enums.ProductErrorMessage;
import com.example.filmsitesi.product.repositories.ProductDao;
import com.example.filmsitesi.product.services.abstracts.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;

    @Override
    public List<ProductResponseDto> getAllProducts() {
        List<Product> productList = productDao.findAll();
        List<ProductResponseDto> productResponseDtoList = ProductMapper.INSTANCE.mapProductListToProductResponseDtoList(productList);
        return productResponseDtoList;
    }

    @Override
    public ProductResponseDto getProductById(Long id) {
        Optional<Product> product = productDao.findById(id);
        if(!product.isPresent()){
            throw new ItemNotFoundException(ProductErrorMessage.PRODUCT_DOES_NOT_EXİST);
        }
        ProductResponseDto productResponseDto = ProductMapper.INSTANCE.mapProductToProductResponseDto(product.get());
        return productResponseDto;
    }

    @Override
    public ProductResponseDto createProduct(ProductCreateRequestDto productCreateRequestDto) {
        if(productCreateRequestDto == null){
            throw new BusinessException(GeneralErrorMessage.VALUES_CANNOT_BE_NULL);
        }
       Product product =  ProductMapper.INSTANCE.mapProductCreateRequestDtoToProduct(productCreateRequestDto);
       product = productDao.save(product);
       ProductResponseDto productResponseDto = ProductMapper.INSTANCE.mapProductToProductResponseDto(product);
       return productResponseDto;
    }

    @Override
    public ProductResponseDto updateProduct(Long id, ProductUpdateRequestDto productUpdateRequestDto) {
        if(!isExist(id)){
            // TODO : throw exception!
            throw new BusinessException(GeneralErrorMessage.VALUES_CANNOT_BE_NULL);
        }
        if(id != productUpdateRequestDto.getId()){
            throw new BusinessException(GeneralErrorMessage.ID_NOT_EQUAL);
        }
        Product product =  ProductMapper.INSTANCE.mapProductUpdateRequestDtoToProduct(productUpdateRequestDto);
        //TODO : checking
        product = productDao.save(product);
        ProductResponseDto productResponseDto = ProductMapper.INSTANCE.mapProductToProductResponseDto(product);
        return productResponseDto;
    }

    @Override
    public void deleteProductById(Long id) {
        if(!isExist(id)){
            // TODO: throw
        }
        productDao.deleteById(id);
    }

    //TODO : these methods should be in a generic service!

    private boolean isExist(Long id){
        Optional<Product> productToFind = productDao.findById(id);
        if(!productToFind.isPresent()){
            return false;
        }
        return true;
    }
}
