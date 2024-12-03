package com.example.asosbe.service;

import com.example.asosbe.model.Product;
import com.example.asosbe.model.Sale;
import com.example.asosbe.repository.SaleRepository;
import com.example.asosbe.service.SaleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

class SaleServiceImplTest {

    @Mock
    private SaleRepository saleRepository;

    @InjectMocks
    private SaleServiceImpl saleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindActiveSaleForProductFound() {
        Product mockProduct = new Product();
        mockProduct.setId(1L);
        LocalDateTime now = LocalDateTime.now();
        Sale mockSale = new Sale();

        when(saleRepository.findActiveSaleForProduct(mockProduct, now)).thenReturn(Optional.of(mockSale));

        Optional<Sale> result = saleService.findActiveSaleForProduct(mockProduct, now);

        assertTrue(result.isPresent());
        verify(saleRepository, times(1)).findActiveSaleForProduct(mockProduct, now);
    }

    @Test
    void testFindActiveSaleForProductNotFound() {
        Product mockProduct = new Product();
        mockProduct.setId(1L);
        LocalDateTime now = LocalDateTime.now();

        when(saleRepository.findActiveSaleForProduct(mockProduct, now)).thenReturn(Optional.empty());

        Optional<Sale> result = saleService.findActiveSaleForProduct(mockProduct, now);

        assertFalse(result.isPresent());
        verify(saleRepository, times(1)).findActiveSaleForProduct(mockProduct, now);
    }
}
