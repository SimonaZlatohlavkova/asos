package products.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import products.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}