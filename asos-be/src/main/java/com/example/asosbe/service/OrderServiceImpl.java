package com.example.asosbe.service;

import com.example.asosbe.dto.OrderRequest;
import com.example.asosbe.dto.OrderResponse;
import com.example.asosbe.exception.NotFoundException;
import com.example.asosbe.mapper.OrderMapper;
import com.example.asosbe.model.*;
import com.example.asosbe.repository.OrderRepository;
import lombok.AllArgsConstructor;
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
    private final IUserService userService;
    private final IOrderProductService orderProductService;

    private final OrderMapper orderMapper;

    @Override
    public List<OrderResponse> getOrdersByUserId(Long userId) {
        log.info("getOrdersByUserId({})", userId);
        return orderRepository.findByUser_Id(userId).stream()
                .map(orderMapper::toOrderResponse)
                .toList();
    }

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest, Long userId) throws NotFoundException {
        log.info("createOrder()");
        Order order = new Order();
        User user = userService.getById(userId);

        Address address = new Address();
        address.setStreet(orderRequest.getAddress().getStreet());
        address.setCity(orderRequest.getAddress().getCity());
        address.setHouseNumber(orderRequest.getAddress().getHouseNumber());
        address.setPostalCode(orderRequest.getAddress().getPostCode());
        address.setCountry(orderRequest.getAddress().getCountry());

        order.setAddress(addressService.save(address));
        order.setDelivery(deliveryService.getDeliveryById(orderRequest.getDeliveryId()));

        BigDecimal totalPrice = orderRequest.getProducts().stream()
                .map(productRequest -> productService.getProductById(productRequest.getId())
                        .getOriginalPrice().multiply(BigDecimal.valueOf(productRequest.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotalPrice(totalPrice);
        order.setUser(user);
        order.setStatus("pending");
        order.setOrderDate(LocalDateTime.now());
        order.setCreatedAt(LocalDateTime.now());

        var newOrder = orderRepository.save(order);
        orderRequest.getProducts().forEach(productRequest -> {
            Product product = productService.getProductById(productRequest.getId());
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrder(newOrder);
            orderProduct.setProduct(product);
            orderProduct.setQuantity(productRequest.getQuantity());
            orderProduct.setPrice(product.getOriginalPrice().multiply(BigDecimal.valueOf(productRequest.getQuantity())));
            orderProductService.save(orderProduct);
        });
        return new OrderResponse(newOrder.getId(), newOrder.getOrderDate(), newOrder.getTotalPrice(),
                newOrder.getDelivery().getPrice(), null, newOrder.getStatus(), null);
    }
}
