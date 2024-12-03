package com.example.asosbe.service;

import com.example.asosbe.model.Address;

public interface IAddressService {

    Address getById(Long addressId);

    Address save(Address address);
}
