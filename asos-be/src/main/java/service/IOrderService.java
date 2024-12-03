package service;

import dto.OrderRequest;
import dto.OrderResponse;
import model.Order;

import java.util.List;

public interface IOrderService {

    Order getOrderById(Long id);
    List<OrderResponse> getOrdersByUserId(Long userId);
    void createOrder(OrderRequest orderRequest);
    void deleteOrder(Long id);
    List<OrderResponse> getOrders();
}
