package com.example.asosbe.service;

import com.example.asosbe.dto.OrderRequest;
import com.example.asosbe.dto.OrderResponse;
import com.example.asosbe.model.Order;
import com.example.asosbe.model.User;

import java.util.List;

public interface IOrderService {

    List<OrderResponse> getOrdersByUserId(Long userId);

    OrderResponse createOrder(OrderRequest orderRequest, User user);
}
