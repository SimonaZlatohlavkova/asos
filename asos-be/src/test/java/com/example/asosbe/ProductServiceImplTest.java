package com.example.asosbe;

import com.example.asosbe.dto.ProductCartRequest;
import com.example.asosbe.dto.ProductFilterRequest;
import com.example.asosbe.dto.ProductResponse;
import com.example.asosbe.exception.NotFoundException;
import com.example.asosbe.model.Product;
import com.example.asosbe.repository.ProductRepository;
import com.example.asosbe.service.ISaleService;
import com.example.asosbe.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ISaleService saleService;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetProductByIdSuccess() {
        Long productId = 1L;
        Product mockProduct = new Product();
        mockProduct.setId(productId);
        mockProduct.setName("Test Product");

        when(productRepository.findById(productId)).thenReturn(Optional.of(mockProduct));

        Product result = productService.getProductById(productId);

        assertNotNull(result);
        assertEquals(productId, result.getId());
        assertEquals("Test Product", result.getName());
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void testGetProductByIdNotFound() {
        Long productId = 1L;

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> productService.getProductById(productId));
        assertEquals("Product not found with ID: " + productId, exception.getMessage());
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void testFilterProductsSuccess() {
        String productName = "Test";
        ProductFilterRequest request = new ProductFilterRequest();
        request.setName(productName);

        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Test Product 1");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Test Product 2");

        when(productRepository.findByNameContainingIgnoreCase(productName)).thenReturn(List.of(product1, product2));

        List<ProductResponse> result = productService.filterProducts(request);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Test Product 1", result.get(0).getName());
        assertEquals("Test Product 2", result.get(1).getName());
        verify(productRepository, times(1)).findByNameContainingIgnoreCase(productName);
    }

    @Test
    void testGetCartProductsSuccess() {
        List<Long> productIds = List.of(1L, 2L);
        ProductCartRequest request = new ProductCartRequest();
        request.setProductIds(productIds);

        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Product 1");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Product 2");

        when(productRepository.findByIdIn(productIds)).thenReturn(List.of(product1, product2));

        List<ProductResponse> result = productService.getCartProducts(request);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Product 1", result.get(0).getName());
        assertEquals("Product 2", result.get(1).getName());
        verify(productRepository, times(1)).findByIdIn(productIds);
    }

    @Test
    void testGetCartProductsWithNullIds() {
        ProductCartRequest request = new ProductCartRequest();
        request.setProductIds(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> productService.getCartProducts(request));
        assertEquals("Product IDs cannot be null or empty", exception.getMessage());
        verifyNoInteractions(productRepository);
    }

    @Test
    void testGetCartProductsWithEmptyIds() {
        ProductCartRequest request = new ProductCartRequest();
        request.setProductIds(List.of());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> productService.getCartProducts(request));
        assertEquals("Product IDs cannot be null or empty", exception.getMessage());
        verifyNoInteractions(productRepository);
    }
}
