package products.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import products.models.Product;
import products.repo.ProductRepository;
import products.service.ProductService;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.getProductById(id);
    }
}