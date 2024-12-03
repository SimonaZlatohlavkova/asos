package com.example.asosbe.service;

import com.example.asosbe.model.Product;
import lombok.AllArgsConstructor;
import com.example.asosbe.model.Sale;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.example.asosbe.repository.SaleRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class SaleServiceImpl implements ISaleService {

    private final SaleRepository saleRepository;

    @Override
    public Optional<Sale> findActiveSaleForProduct(Product product, LocalDateTime now) {
        log.info("findActiveSaleForProduct({})", product.getId());
        return saleRepository.findActiveSaleForProduct(product, now);
    }
}
