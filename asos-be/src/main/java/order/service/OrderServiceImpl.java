package order.service;

import lombok.AllArgsConstructor;
import order.dtos.OrderRequest;
import order.dtos.OrderResponse;
import order.models.Order;
import order.repo.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements IOrderService {

    private final OrderRepository orderRepository;

    @Override
    public Order getOrderById(Long id) {
        if (orderRepository.findById(id).isEmpty()){
            //TODO custom exception
            return null;
        }
        return orderRepository.findById(id).get();
    }

    @Override
    public Order createOrder(OrderRequest orderRequest) {
        //TODO handle custom exception
        //TODO create order logic
        return null;
    }

    @Override
    public void deleteOrder(Long id) {

    }

    @Override
    public List<OrderResponse> getOrders() {
        return List.of();
    }
}
