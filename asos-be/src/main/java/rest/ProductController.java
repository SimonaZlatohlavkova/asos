package rest;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import dto.ProductCartRequest;
import dto.ProductFilterRequest;
import dto.ProductResponse;
import model.Product;
import service.IProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private final IProductService iProductService;

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return iProductService.getProductById(id);
    }

    @PostMapping("/filter")
    public ResponseEntity<List<ProductResponse>> filterProduct(@RequestBody ProductFilterRequest productFilterRequest) {
        List<ProductResponse> productResponses = iProductService.filterProducts(productFilterRequest);
        return ResponseEntity.ok(productResponses);
    }

    @PostMapping("/cart")
    public ResponseEntity<List<ProductResponse>> getProductsFromCart(@RequestBody ProductCartRequest productCartRequest) {
        List<ProductResponse> productResponses = iProductService.getCartProducts(productCartRequest);
        return ResponseEntity.ok(productResponses);
    }
}