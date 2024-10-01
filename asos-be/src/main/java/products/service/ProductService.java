package products.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import products.models.Product;
import products.repo.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product getProductById(Long id) {
        if (productRepository.findById(id).isEmpty()){
            //TODO custom exception
            return null;
        }
        return productRepository.findById(id).get();
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}

