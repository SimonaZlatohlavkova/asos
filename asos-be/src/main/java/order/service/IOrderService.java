package order.service;

import order.dtos.OrderRequest;
import order.dtos.OrderResponse;
import order.models.Order;

import java.util.List;

public interface IOrderService {

    Order getOrderById(Long id);
    Order createOrder(OrderRequest orderRequest);
    void deleteOrder(Long id);
    List<OrderResponse> getOrders();
}
