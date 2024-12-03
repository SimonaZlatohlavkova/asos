package com.example.asosbe.service;

import com.example.asosbe.exception.NotFoundException;
import com.example.asosbe.model.Address;
import com.example.asosbe.repository.AddressRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class AddressServiceImpl implements IAddressService {

    private final AddressRepository addressRepository;

    @Override
    public Address getById(Long addressId) throws NotFoundException {
        log.info("getById({})", addressId);
        var address = addressRepository.findById(addressId);
        if (address.isEmpty()){
            throw new NotFoundException("Requested address not found");
        }
        return address.get();
    }

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }
}

