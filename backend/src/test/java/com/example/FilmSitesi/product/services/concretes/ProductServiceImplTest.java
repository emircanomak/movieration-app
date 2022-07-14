package com.example.filmsitesi.product.services.concretes;

import com.example.filmsitesi.category.entities.Category;
import com.example.filmsitesi.generic.exceptions.BusinessException;
import com.example.filmsitesi.generic.exceptions.GeneralErrorMessage;
import com.example.filmsitesi.generic.exceptions.ItemNotFoundException;
import com.example.filmsitesi.product.dtos.ProductCreateRequestDto;
import com.example.filmsitesi.product.dtos.ProductResponseDto;
import com.example.filmsitesi.product.dtos.ProductUpdateRequestDto;
import com.example.filmsitesi.product.entities.Product;
import com.example.filmsitesi.product.repositories.ProductDao;
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
class ProductServiceImplTest {

    @Mock
    private ProductDao productDao;
    @InjectMocks
    private ProductServiceImpl productServiceImpl;
    @Test
    void shouldGetAllProductsAsEmptyList() {
       List<Product> productList = productDao.findAll();
       Mockito.when(productDao.findAll()).thenReturn(productList);
       List<ProductResponseDto> productResponseDtoList =  productServiceImpl.getAllProducts();
       assertEquals(0,productResponseDtoList.size());
    }
    @Test
    void shouldGetAllProductsWhileAvailableOneInstance() {
        Product product = Mockito.mock(Product.class);
        List<Product> productList = productDao.findAll();
        productList.add(product);
        Mockito.when(productDao.findAll()).thenReturn(productList);
        List<ProductResponseDto> productResponseDtoList =  productServiceImpl.getAllProducts();
        assertEquals(1,productResponseDtoList.size());
    }
    @Test
    void shouldGetProductById() {
        Product product = Mockito.mock(Product.class);
        Mockito.when(product.getId()).thenReturn(1L);
        Mockito.when(productDao.findById(1L)).thenReturn(Optional.of(product));
        ProductResponseDto productResponseDto = productServiceImpl.getProductById(1L);
        assertEquals(1L, productResponseDto.getId());
    }
    @Test
    void shouldNotGetProductByIdWhileItIsNotAvailable() {
       Mockito.when(productDao.findById(anyLong())).thenThrow(ItemNotFoundException.class);
       assertThrows(ItemNotFoundException.class, ()->productServiceImpl.getProductById(anyLong()));
    }

    @Test
    void shouldCreateProduct() {
       ProductCreateRequestDto productCreateRequestDto = Mockito.mock(ProductCreateRequestDto.class);
       Product product = Mockito.mock(Product.class);
       Mockito.when(productDao.save(any())).thenReturn(product);
       Mockito.when(product.getId()).thenReturn(1L);
       ProductResponseDto productResponseDto =  productServiceImpl.createProduct(productCreateRequestDto);
        assertEquals(1L, productResponseDto.getId());
    }
    @Test
    void shouldNotCreateProductWhileParameterIsNull() {
        BusinessException businessException = assertThrows(BusinessException.class,()-> productServiceImpl.createProduct(null));
        assertEquals(GeneralErrorMessage.VALUES_CANNOT_BE_NULL, businessException.getBaseErrorMessage());
    }
    @Test
    void updateProduct() {
        ProductUpdateRequestDto productUpdateRequestDto =  Mockito.mock(ProductUpdateRequestDto.class);
        Mockito.when(productUpdateRequestDto.getId()).thenReturn(1L);


        Product product = Mockito.mock(Product.class);
        Mockito.when(productDao.findById(anyLong())).thenReturn(Optional.of(product));
        Category category = Mockito.mock(Category.class);
        Mockito.when(category.getId()).thenReturn(2L);
        Mockito.when(product.getCategory()).thenReturn(category);


        Mockito.when(productDao.save(any())).thenReturn(product);
        ProductResponseDto productResponseDto = productServiceImpl.updateProduct(1L,productUpdateRequestDto);

        assertEquals(2L,productResponseDto.getCategoryId());

    }
    @Test
    void shouldNotUpdateProductIfIdDoesNotEqualGivenObjectId() {
        ProductUpdateRequestDto productUpdateRequestDto =  Mockito.mock(ProductUpdateRequestDto.class);
        Mockito.when(productUpdateRequestDto.getId()).thenReturn(2L);
        Product product = Mockito.mock(Product.class);
        Mockito.when(productDao.findById(anyLong())).thenReturn(Optional.of(product));
        BusinessException businessException = assertThrows(
                BusinessException.class,()-> productServiceImpl.updateProduct(1L,productUpdateRequestDto));
        assertEquals(GeneralErrorMessage.ID_NOT_EQUAL, businessException.getBaseErrorMessage());
    }
    @Test
    void shouldNotUpdateProductIfGivenObjectIsNull() {
      BusinessException businessException = assertThrows(
              BusinessException.class, ()->productServiceImpl.updateProduct(1L,null));
      assertEquals(GeneralErrorMessage.VALUES_CANNOT_BE_NULL,businessException.getBaseErrorMessage());
    }
    @Test
    void deleteProductById() {
        Mockito.doNothing().when(productDao).deleteById(anyLong());
        productServiceImpl.deleteProductById(1L);
        Mockito.verify(productDao).deleteById(anyLong());
    }
    @Test
    void shouldNotDeleteProductById() {
        Mockito.doThrow(NoSuchElementException.class).when(productDao).deleteById(anyLong());
        assertThrows(NoSuchElementException.class, ()->productServiceImpl.deleteProductById(1L));
        Mockito.verify(productDao).deleteById(anyLong());
    }
}