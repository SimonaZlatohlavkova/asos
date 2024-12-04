package com.example.asosbe.e2e;

import com.example.asosbe.config.TestSecurityConfiguration;
import com.example.asosbe.model.Product;
import com.example.asosbe.rest.ProductController;

import com.example.asosbe.dto.*;
import com.example.asosbe.service.IProductService;
import com.example.asosbe.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import javax.security.auth.login.LoginException;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(controllers = ProductController.class)
@Import(TestSecurityConfiguration.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductService productService;

    @MockBean
    private IUserService userService;

    @Test
    void testGetProductSuccess() throws Exception {
        String jwt = "valid.jwt.token";
        Long productId = 1L;

        Product product = new Product(
                1L, "Product 1", "Description 1", "Url",  BigDecimal.valueOf(100), 100L, LocalDateTime.now());

        when(userService.getUserIdByToken(jwt)).thenReturn(1L);
        when(productService.getProductById(productId)).thenReturn(product);

        mockMvc.perform(get("/product/{id}", productId)
                        .header("Authorization", jwt)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Product 1"))
                .andExpect(jsonPath("$.description").value("Description 1"))
                .andExpect(jsonPath("$.originalPrice").value(100));
    }

    @Test
    void testFilterProductSuccess() throws Exception {
        String jwt = "valid.jwt.token";
        ProductFilterRequest filterRequest = new ProductFilterRequest("Product");
        List<ProductResponse> filteredProducts = List.of(
                new ProductResponse(1L, "Product 1", "Description 1", "Url 1",  BigDecimal.valueOf(100), BigDecimal.valueOf(80), 100L),
                new ProductResponse(2L, "Product 2", "Description 2", "Url 2",  BigDecimal.valueOf(200), BigDecimal.valueOf(80), 100L)
        );

        when(userService.getUserIdByToken(jwt)).thenReturn(1L);
        when(productService.filterProducts(any(ProductFilterRequest.class))).thenReturn(filteredProducts);

        mockMvc.perform(post("/product/filter")
                        .header("Authorization", jwt)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "name": "Product"
                            }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Product 1"))
                .andExpect(jsonPath("$[0].description").value("Description 1"))
                .andExpect(jsonPath("$[0].originalPrice").value(100))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Product 2"))
                .andExpect(jsonPath("$[1].description").value("Description 2"))
                .andExpect(jsonPath("$[1].originalPrice").value(200));
    }

    @Test
    void testGetProductsFromCartSuccess() throws Exception {
        String jwt = "valid.jwt.token";
        ProductCartRequest cartRequest = new ProductCartRequest(List.of(1L, 2L));
        List<ProductResponse> cartProducts = List.of(
                new ProductResponse(1L, "Product 1", "Description 1", "Url 1",  BigDecimal.valueOf(100), BigDecimal.valueOf(80), 100L),
                new ProductResponse(2L, "Product 2", "Description 2", "Url 2",  BigDecimal.valueOf(200), BigDecimal.valueOf(80), 100L)
        );

        when(userService.getUserIdByToken(jwt)).thenReturn(1L);
        when(productService.getCartProducts(any(ProductCartRequest.class))).thenReturn(cartProducts);

        mockMvc.perform(post("/product/cart")
                        .header("Authorization", jwt)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "productIds": [1, 2]
                            }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Product 1"))
                .andExpect(jsonPath("$[0].description").value("Description 1"))
                .andExpect(jsonPath("$[0].originalPrice").value(100))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Product 2"))
                .andExpect(jsonPath("$[1].description").value("Description 2"))
                .andExpect(jsonPath("$[1].originalPrice").value(200));
    }

    @Test
    void testGetProductUnauthorized() throws Exception {
        String jwt = "invalid.jwt.token";

        when(userService.getUserIdByToken(jwt)).thenThrow(new LoginException("Invalid token"));

        mockMvc.perform(get("/product/{id}", 1)
                        .header("Authorization", jwt)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("Invalid token"))
                .andExpect(jsonPath("$.status").value(401));
    }
}

