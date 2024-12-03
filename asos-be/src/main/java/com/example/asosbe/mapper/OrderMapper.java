package com.example.asosbe.mapper;

import com.example.asosbe.dto.AddressResponse;
import com.example.asosbe.dto.OrderProductResponse;
import com.example.asosbe.dto.OrderResponse;
import com.example.asosbe.model.Address;
import com.example.asosbe.model.Order;
import com.example.asosbe.model.OrderProduct;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

    public OrderResponse toOrderResponse(Order order) {
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setDate(order.getOrderDate());
        response.setSummarization(order.getTotalPrice());
        response.setDeliveryCost(order.getDelivery().getPrice());
        response.setAddress(toAddressResponse(order.getAddress()));
        response.setStatus(order.getStatus());
        response.setProducts(order.getOrderProducts().stream()
                .map(this::toOrderProductResponse)
                .toList());
        return response;
    }

    private AddressResponse toAddressResponse(Address address) {
        AddressResponse response = new AddressResponse();
        response.setStreet(address.getStreet());
        response.setCity(address.getCity());
        response.setHouseNumber(address.getHouseNumber());
        response.setPostCode(address.getPostalCode());
        response.setCountry(address.getCountry());
        return response;
    }

    private OrderProductResponse toOrderProductResponse(OrderProduct orderProduct) {
        OrderProductResponse response = new OrderProductResponse();
        response.setId(orderProduct.getProduct().getId());
        response.setQuantity(orderProduct.getQuantity());
        response.setPrice(orderProduct.getPrice());
        response.setName(orderProduct.getProduct().getName());
        response.setUrl(orderProduct.getProduct().getUrl());
        return response;
    }
}