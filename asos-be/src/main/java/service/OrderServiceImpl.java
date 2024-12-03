package service;

import dto.AddressResponse;
import exception.NotFoundException;
import mapper.OrderMapper;
import repository.AddressRepository;
import repository.DeliveryRepository;
import lombok.AllArgsConstructor;
import dto.OrderProductResponse;
import dto.OrderRequest;
import dto.OrderResponse;
import model.Order;
import repository.OrderRepository;
import org.springframework.stereotype.Service;
import model.Product;
import repository.ProductRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements IOrderService {

    private final OrderRepository orderRepository;
    private final IProductService productService;
    private final IAddressService addressService;
    private final IDeliveryService deliveryService;

    private final OrderMapper orderMapper;

    @Override
    public Order getOrderById(Long id) {
        var order= orderRepository.findById(id);
        if (order.isEmpty()){
           throw new NotFoundException("Order with id " + id + " not found");
        }
        return order.get();
    }

    @Override
    public List<OrderResponse> getOrdersByUserId(Long userId) {
        return orderRepository.findByUser_UserId(userId).stream()
                .map(orderMapper::toOrderResponse)
                .toList();
    }

    @Override
    public void createOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setAddress(addressService.getById(orderRequest.getAddress().getAddressId()));
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
