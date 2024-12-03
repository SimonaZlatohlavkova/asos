package com.example.asosbe.service;

import com.example.asosbe.exception.NotFoundException;
import com.example.asosbe.model.Address;
import com.example.asosbe.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements IAddressService {

    private final AddressRepository addressRepository;

    @Override
    public Address getById(Long addressId) throws NotFoundException {
        var address = addressRepository.findById(addressId);
        if (address.isEmpty()){
            throw new NotFoundException("Requested address not found");
        }
        return address.get();
    }
}

