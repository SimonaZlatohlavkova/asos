package com.example.asosbe.e2e;

import com.example.asosbe.config.TestSecurityConfiguration;
import com.example.asosbe.dto.*;
import com.example.asosbe.model.User;
import com.example.asosbe.rest.OrderController;
import com.example.asosbe.service.IOrderService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = OrderController.class)
@Import(TestSecurityConfiguration.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IOrderService orderService;

    @MockBean
    private IUserService userService;

    @Test
    void testCreateOrderSuccess() throws Exception {
        String jwt = "valid.jwt.token";
        Long userId = 1L;

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setAddress(new AddressRequest("123 Test St", "Test City", "1A", "12345", "Country"));
        orderRequest.setDeliveryId(2L);
        orderRequest.setProducts(List.of(
                new ProductRequest(1L, BigDecimal.valueOf(50), 2L),
                new ProductRequest(2L, BigDecimal.valueOf(30), 1L)
        ));

        AddressResponse addressResponse = new AddressResponse("123 Test St", "12345", "1A", "Test City", "Country");

        OrderResponse orderResponse = new OrderResponse(
                1001L,
                LocalDateTime.now(),
                BigDecimal.valueOf(130),
                BigDecimal.valueOf(5),
                addressResponse,
                "pending",
                List.of(
                        new OrderProductResponse(1L, BigDecimal.valueOf(50), 2L, "Product 1", "Product 1"),
                        new OrderProductResponse(2L, BigDecimal.valueOf(50), 2L, "Product 1", "Product 1")
                )
        );

        when(userService.getUserIdByToken(jwt)).thenReturn(userId);
        when(userService.getById(userId)).thenReturn(new User(1L, "John", "Doe", "john@example.com", "hashedPassword", LocalDateTime.now()));
        when(orderService.createOrder(any(OrderRequest.class), any(User.class))).thenReturn(orderResponse);

        mockMvc.perform(post("/order")
                        .header("Authorization", jwt)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "address": {
                                "street": "123 Test St",
                                "city": "Test City",
                                "houseNumber": "1A",
                                "postCode": "12345",
                                "country": "Country"
                            },
                            "deliveryId": 2,
                            "products": [
                                {"id": 1, "price": 50, "quantity": 2},
                                {"id": 2, "price": 30, "quantity": 1}
                            ]
                        }
                    """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1001))
                .andExpect(jsonPath("$.date").exists())
                .andExpect(jsonPath("$.summarization").value(130))
                .andExpect(jsonPath("$.deliveryCost").value(5))
                .andExpect(jsonPath("$.address.street").value("123 Test St"))
                .andExpect(jsonPath("$.address.city").value("Test City"))
                .andExpect(jsonPath("$.address.houseNumber").value("12345"))
                .andExpect(jsonPath("$.address.postCode").value("1A"))
                .andExpect(jsonPath("$.address.country").value("Country"))
                .andExpect(jsonPath("$.status").value("pending"))
                .andExpect(jsonPath("$.products.length()").value(2))
                .andExpect(jsonPath("$.products[0].id").value(1))
                .andExpect(jsonPath("$.products[0].price").value(50))
                .andExpect(jsonPath("$.products[0].quantity").value(2))
                .andExpect(jsonPath("$.products[0].name").value("Product 1"))
                .andExpect(jsonPath("$.products[0].url").value("Product 1"))
                .andExpect(jsonPath("$.products[1].id").value(2))
                .andExpect(jsonPath("$.products[1].price").value(50))
                .andExpect(jsonPath("$.products[1].quantity").value(2))
                .andExpect(jsonPath("$.products[1].name").value("Product 1"))
                .andExpect(jsonPath("$.products[1].url").value("Product 1"));
    }


    @Test
    void testCreateOrderUnauthorized() throws Exception {
        String jwt = "invalid.jwt.token";

        when(userService.getUserIdByToken(jwt)).thenThrow(new LoginException("Invalid token"));

        mockMvc.perform(post("/order")
                        .header("Authorization", jwt)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "address": {
                                    "street": "123 Test St",
                                    "city": "Test City",
                                    "houseNumber": "1A",
                                    "postCode": "12345",
                                    "country": "Country"
                                },
                                "deliveryId": 2,
                                "products": [
                                    {"id": 1, "price": 50, "quantity": 2},
                                    {"id": 2, "price": 30, "quantity": 1}
                                ]
                            }
                            """))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("Invalid token"))
                .andExpect(jsonPath("$.status").value(401));
    }
}

