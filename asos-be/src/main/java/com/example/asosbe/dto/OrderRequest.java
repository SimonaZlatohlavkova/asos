package com.example.asosbe.dto;

import com.example.asosbe.model.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderRequest {
    private Long deliveryId;
    private Address address;
    private List<ProductRequest> products;
}