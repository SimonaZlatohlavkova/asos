package products.service;

import products.dtos.ProductResponse;
import products.models.Product;

import java.util.List;

public interface IProductService {
    Product getProductById(Long id);
    Product saveProduct(Product product);
    void deleteProduct(Long id);
    List<Product> getAllProducts();
}
