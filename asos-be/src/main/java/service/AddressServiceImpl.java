package service;

import exception.NotFoundException;
import model.Address;
import repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements IAddressService {

    private final AddressRepository addressRepository;

    @Override
    public Address getById(Long addressId) throws NotFoundException{
        var address = addressRepository.findById(addressId);
        if (address.isEmpty()){
            throw new NotFoundException("Requested address not found");
        }
        return address.get();
    }
}

