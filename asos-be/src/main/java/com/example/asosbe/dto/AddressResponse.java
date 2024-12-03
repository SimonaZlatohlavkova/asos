package com.example.asosbe.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressResponse {
    private String street;
    private String houseNumber;
    private String postCode;
    private String city;
    private String country;
}
