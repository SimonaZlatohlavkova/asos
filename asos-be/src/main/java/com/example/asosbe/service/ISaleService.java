package com.example.asosbe.service;

import com.example.asosbe.model.Product;
import com.example.asosbe.model.Sale;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ISaleService {

    Optional<Sale> findActiveSaleForProduct(Product product, LocalDateTime now);
}
