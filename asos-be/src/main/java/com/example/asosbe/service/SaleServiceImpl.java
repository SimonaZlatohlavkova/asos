package com.example.asosbe.service;

import lombok.AllArgsConstructor;
import com.example.asosbe.model.Sale;
import org.springframework.stereotype.Service;
import com.example.asosbe.repository.SaleRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SaleServiceImpl implements ISaleService {

    private final SaleRepository saleRepository;

    @Override
    public Optional<Sale> findActiveSaleForProduct(Long productId, LocalDateTime now) {
        return saleRepository.findActiveSaleForProduct(productId, now);
    }
}
