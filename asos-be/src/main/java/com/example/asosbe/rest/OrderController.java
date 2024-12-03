package com.example.asosbe.rest;

import com.example.asosbe.dto.OrderRequest;
import com.example.asosbe.service.IOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {

    private IOrderService iOrderService;

    @PostMapping()
    public ResponseEntity<String> createorder(@RequestBody OrderRequest orderRequest) {
        iOrderService.createOrder(orderRequest);
        return ResponseEntity.ok("Order created");
    }
}
