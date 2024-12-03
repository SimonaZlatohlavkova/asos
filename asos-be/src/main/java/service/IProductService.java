package service;

import dto.ProductCartRequest;
import dto.ProductFilterRequest;
import dto.ProductResponse;
import model.Product;

import java.util.List;

public interface IProductService {
    Product getProductById(Long id);
    Product saveProduct(Product product);
    void deleteProduct(Long id);
    List<Product> getAllProducts();
    List<ProductResponse> filterProducts(ProductFilterRequest request);
    List<ProductResponse> getCartProducts(ProductCartRequest request);
}
