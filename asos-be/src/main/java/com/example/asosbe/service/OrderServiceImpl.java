package com.example.asosbe.service;

import com.example.asosbe.dto.OrderRequest;
import com.example.asosbe.dto.OrderResponse;
import com.example.asosbe.exception.NotFoundException;
import com.example.asosbe.mapper.OrderMapper;
import com.example.asosbe.repository.OrderRepository;
import lombok.AllArgsConstructor;
import com.example.asosbe.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class OrderServiceImpl implements IOrderService {

    private final OrderRepository orderRepository;

    private final IProductService productService;
    private final IAddressService addressService;
    private final IDeliveryService deliveryService;

    private final OrderMapper orderMapper;

    @Override
    public Order getOrderById(Long id) {
        log.info("getOrderById({})", id);
        var order= orderRepository.findById(id);
        if (order.isEmpty()){
           throw new NotFoundException("Order with id " + id + " not found");
        }
        return order.get();
    }

    @Override
    public List<OrderResponse> getOrdersByUserId(Long userId) {
        log.info("getOrdersByUserId({})", userId);
        return orderRepository.findByUser_Id(userId).stream()
                .map(orderMapper::toOrderResponse)
                .toList();
    }

    @Override
    public void createOrder(OrderRequest orderRequest) {
        log.info("createOrder()");
        Order order = new Order();
        order.setAddress(addressService.getById(orderRequest.getAddress().getId()));
        order.setDelivery(deliveryService.getDeliveryById(orderRequest.getDeliveryId()));

        BigDecimal totalPrice = orderRequest.getProducts().stream()
                .map(productRequest -> productService.getProductById(productRequest.getId())
                        .getOriginalPrice().multiply(BigDecimal.valueOf(productRequest.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotalPrice(totalPrice);
        order.setStatus("pending");
        order.setOrderDate(LocalDateTime.now());

        orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<OrderResponse> getOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toOrderResponse)
                .toList();
    }
}
