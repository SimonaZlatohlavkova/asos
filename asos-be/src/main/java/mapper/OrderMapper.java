package mapper;

import dto.AddressResponse;
import dto.OrderProductResponse;
import dto.OrderResponse;
import model.Address;
import model.Order;
import model.OrderProduct;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

    public OrderResponse toOrderResponse(Order order) {
        OrderResponse response = new OrderResponse();
        response.setOrderId(order.getOrderId());
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
        response.setId(orderProduct.getProduct().getProductId());
        response.setQuantity(orderProduct.getQuantity());
        response.setPrice(orderProduct.getPrice());
        response.setName(orderProduct.getProduct().getName());
        response.setUrl(orderProduct.getProduct().getUrl());
        return response;
    }
}