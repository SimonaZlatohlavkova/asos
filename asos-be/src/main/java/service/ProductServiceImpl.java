package service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import dto.ProductCartRequest;
import dto.ProductFilterRequest;
import dto.ProductResponse;
import model.Product;
import repository.ProductRepository;
import model.Sale;
import repository.SaleRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Cannot delete, product not found with ID: " + id);
        }
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<ProductResponse> filterProducts(ProductFilterRequest request) {
        List<Product> products = productRepository.findByNameContainingIgnoreCase(request.getName());
        return getProductsAsResponses(products);
    }

    public List<ProductResponse> getProductsAsResponses(List<Product> products) {
        return products.stream()
                .map(this::mapToProductResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> getCartProducts(ProductCartRequest request) {
        if (request.getProductIds() == null || request.getProductIds().isEmpty()) {
            throw new IllegalArgumentException("Product IDs cannot be null or empty");
        }
        List<Product> products =  productRepository.findByIdIn(request.getProductIds());
        return getProductsAsResponses(products);
    }

    public List<ProductResponse> getAllProductsAsResponses() {
        return productRepository.findAll().stream()
                .map(this::mapToProductResponse)
                .collect(Collectors.toList());
    }



    private ProductResponse mapToProductResponse(Product product) {
        Optional<Sale> activeSale = saleRepository.findActiveSaleForProduct(
                product.getProductId(),
                LocalDateTime.now()
        );

        ProductResponse response = new ProductResponse();

        response.setProductId(product.getProductId());
        response.setName(product.getName());
        response.setUrl(product.getUrl());
        response.setDescription(product.getDescription());
        response.setOriginalPrice(product.getOriginalPrice());
        response.setSalePrice(activeSale.map(Sale::getSalePrice).orElse(null));
        response.setStock(product.getStock());

        return response;
    }
}

