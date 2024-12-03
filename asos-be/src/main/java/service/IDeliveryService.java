package service;

import dto.DeliveryResponse;
import model.Delivery;

import java.util.List;

public interface IDeliveryService {

    Delivery getDeliveryById(Long id);
    Delivery saveDelivery(Delivery delivery);
    void deleteDelivery(Long id);
    List<DeliveryResponse> getDeliveries();
}
