package com.example.asosbe.service;

import com.example.asosbe.dto.ProductResponse;
import com.example.asosbe.model.Product;
import com.example.asosbe.dto.ProductCartRequest;
import com.example.asosbe.dto.ProductFilterRequest;

import java.util.List;

public interface IProductService {
    Product getProductById(Long id);
    List<ProductResponse> filterProducts(ProductFilterRequest request);
    List<ProductResponse> getCartProducts(ProductCartRequest request);
}
