package order.service;

import address.dtos.AddressResponse;
import address.repo.AddressRepository;
import delivery.repo.DeliveryRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import order.dtos.OrderProductResponse;
import order.dtos.OrderRequest;
import order.dtos.OrderResponse;
import order.models.Order;
import order.repo.OrderRepository;
import org.springframework.stereotype.Service;
import products.dtos.ProductResponse;
import products.models.Product;
import products.repo.ProductRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements IOrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final AddressRepository addressRepository;
    private final DeliveryRepository deliveryRepository;

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
        Order order = new Order();

        order.setAddress(addressRepository.findById(orderRequest.getAddress().getAddressId())
                .orElseThrow(() -> new RuntimeException("Address not found")));

        order.setDelivery(deliveryRepository.findById(orderRequest.getDeliveryId())
                .orElseThrow(() -> new RuntimeException("Delivery not found")));

        BigDecimal totalPrice = orderRequest.getProducts().stream()
                .map(productRequest -> productRepository.findById(productRequest.getId())
                        .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productRequest.getId()))
                        .getOriginalPrice().multiply(BigDecimal.valueOf(productRequest.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotalPrice(totalPrice);
        order.setStatus("pending");
        order.setOrderDate(LocalDateTime.now());

        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<OrderResponse> getOrders() {
        return orderRepository.findAll().stream()
                .map(this::convertOrderToOrderResponse)
                .collect(Collectors.toList());
    }

    private OrderResponse convertOrderToOrderResponse(Order order) {
        OrderResponse response = new OrderResponse();
        response.setOrderId(order.getOrderId());
        response.setDate(order.getOrderDate());
        response.setSummarization(order.getTotalPrice());
        response.setDeliveryCost(order.getDelivery().getPrice());
        response.setStatus(order.getStatus());

        AddressResponse addressResponse = new AddressResponse();
        addressResponse.setStreet(order.getAddress().getStreet());
        addressResponse.setCity(order.getAddress().getCity());
        addressResponse.setCountry(order.getAddress().getCountry());
        addressResponse.setPostCode(order.getAddress().getPostalCode());
//        addressResponse.setHouseNumber();
        response.setAddress(addressResponse);

        response.setProducts(order.getOrderProducts().stream()
                .map(orderProduct -> {
                    Product product = productRepository.findById(orderProduct.getOrderProductId())
                            .orElseThrow(() -> new RuntimeException("Product not found with ID: " + orderProduct.getOrderProductId()));
                    OrderProductResponse orderProductResponse = new OrderProductResponse();
                    orderProductResponse.setId(orderProduct.getOrderProductId());
                    orderProductResponse.setPrice(orderProduct.getPrice());
                    orderProductResponse.setQuantity(orderProduct.getQuantity());
                    orderProductResponse.setName(product.getName());
                    orderProductResponse.setUrl(product.getUrl());

                    return orderProductResponse;
                })
                .collect(Collectors.toList()));
        return response;
    }
}
