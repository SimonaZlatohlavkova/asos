package com.example.asosbe.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderResponse {
    private Long orderId;
    private LocalDateTime date;
    private BigDecimal summarization;
    private BigDecimal deliveryCost;
    private AddressResponse address;
    private String status;
    private List<OrderProductResponse> products;
}