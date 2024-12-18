package com.example.asosbe.service;

import com.example.asosbe.dto.ProductResponse;
import com.example.asosbe.exception.NotFoundException;
import com.example.asosbe.model.Product;
import com.example.asosbe.model.Sale;
import com.example.asosbe.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.asosbe.dto.ProductCartRequest;
import com.example.asosbe.dto.ProductFilterRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;
    private final ISaleService saleService;

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found with ID: " + id));
    }

    @Override
    public List<ProductResponse> filterProducts(ProductFilterRequest request) {
        List<Product> products = productRepository.findByNameContainingIgnoreCase(request.getName());
        return getProductsAsResponses(products);
    }

    @Override
    public List<ProductResponse> getCartProducts(ProductCartRequest request) {
        if (request.getProductIds() == null || request.getProductIds().isEmpty()) {
            throw new IllegalArgumentException("Product IDs cannot be null or empty");
        }
        List<Product> products =  productRepository.findByIdIn(request.getProductIds());
        return getProductsAsResponses(products);
    }

    private ProductResponse mapToProductResponse(Product product) {
        Optional<Sale> activeSale = saleService.findActiveSaleForProduct(product, LocalDateTime.now());

        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setUrl(product.getUrl());
        response.setDescription(product.getDescription());
        response.setOriginalPrice(product.getOriginalPrice());
        response.setSalePrice(activeSale.map(Sale::getSalePrice).orElse(null));
        response.setStock(product.getStock());

        return response;
    }

    public List<ProductResponse> getProductsAsResponses(List<Product> products) {
        return products.stream()
                .map(this::mapToProductResponse)
                .toList();
    }
}

