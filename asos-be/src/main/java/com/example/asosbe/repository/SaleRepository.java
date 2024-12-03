package com.example.asosbe.repository;

import com.example.asosbe.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.asosbe.model.Sale;

import java.time.LocalDateTime;
import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT s FROM Sale s WHERE s.product = :product AND :currentDate BETWEEN s.dateFrom AND s.dateTo")
    Optional<Sale> findActiveSaleForProduct(@Param("product") Product product, @Param("currentDate") LocalDateTime currentDate);
}
