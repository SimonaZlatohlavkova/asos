package com.example.asosbe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductResponse {
    private Long id;
    private BigDecimal price;
    private Long quantity;
    private String name;
    private String url;
}