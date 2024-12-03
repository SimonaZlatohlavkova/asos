package com.example.asosbe.service;

import com.example.asosbe.model.Sale;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ISaleService {

    Optional<Sale> findActiveSaleForProduct(Long productId, LocalDateTime now);
}
