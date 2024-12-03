package com.example.asosbe.service;

import com.example.asosbe.model.OrderProduct;
import com.example.asosbe.repository.OrderProductRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderProductServiceImpl implements IOrderProductService{

    OrderProductRepository orderProductRepository;


    @Override
    public void save(OrderProduct orderProduct) {
        orderProductRepository.save(orderProduct);
    }
}
