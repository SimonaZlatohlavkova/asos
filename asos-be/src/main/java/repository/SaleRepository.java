package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import model.Sale;

import java.time.LocalDateTime;
import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT s FROM Sale s WHERE s.product = :productId AND :currentDate BETWEEN s.dateFrom AND s.dateTo")
    Optional<Sale> findActiveSaleForProduct(@Param("productId") Long productId, @Param("currentDate") LocalDateTime currentDate);
}
