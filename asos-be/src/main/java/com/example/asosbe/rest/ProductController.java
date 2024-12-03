package com.example.asosbe.rest;

import com.example.asosbe.dto.ProductResponse;
import com.example.asosbe.exception.ErrorResponse;
import com.example.asosbe.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.asosbe.dto.ProductCartRequest;
import com.example.asosbe.dto.ProductFilterRequest;
import com.example.asosbe.service.IProductService;

import javax.security.auth.login.LoginException;
import java.util.List;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private final IProductService productService;

    private final IUserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProduct(@PathVariable Long id, @RequestHeader("Authorization") String jwt) {
        try{
            userService.getUserIdByToken(jwt);
        }
        catch(LoginException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse(e.getMessage(),
                    HttpStatus.UNAUTHORIZED.value(), e.getClass().getName()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductById(id));
    }

    @PostMapping("/filter")

    public ResponseEntity<Object> filterProduct(@RequestBody ProductFilterRequest productFilterRequest,
                                                               @RequestHeader("Authorization") String jwt) {
        try{
            userService.getUserIdByToken(jwt);
        }
        catch(LoginException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse(e.getMessage(),
                    HttpStatus.UNAUTHORIZED.value(), e.getClass().getName()));
        }
        List<ProductResponse> productResponses = productService.filterProducts(productFilterRequest);
        return ResponseEntity.ok(productResponses);
    }

    @PostMapping("/cart")
    public ResponseEntity<Object> getProductsFromCart(@RequestBody ProductCartRequest productCartRequest,
                                                                @RequestHeader("Authorization") String jwt) {
        try{
            userService.getUserIdByToken(jwt);
        }
        catch(LoginException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse(e.getMessage(),
                    HttpStatus.UNAUTHORIZED.value(), e.getClass().getName()));
        }
        List<ProductResponse> productResponses = productService.getCartProducts(productCartRequest);
        return ResponseEntity.ok(productResponses);
    }
}