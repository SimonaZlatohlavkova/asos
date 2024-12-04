package com.example.asosbe.service;

import com.example.asosbe.model.OrderProduct;
import com.example.asosbe.repository.OrderProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class OrderProductServiceImpl implements IOrderProductService{

    private final OrderProductRepository orderProductRepository;


    @Override
    public void save(OrderProduct orderProduct) {
        orderProductRepository.save(orderProduct);
    }
}
