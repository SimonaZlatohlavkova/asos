package service;

import model.Address;

import java.util.Optional;

public interface IAddressService {

    Address getById(Long addressId);
}
