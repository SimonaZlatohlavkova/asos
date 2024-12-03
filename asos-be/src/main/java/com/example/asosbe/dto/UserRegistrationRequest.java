package com.example.asosbe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRegistrationRequest {

    @NotBlank(message = "Name cannot be blank")
    @Size(max = 50, message = "Name should be at most 50 characters")
    private String name;

    @NotBlank(message = "Surname cannot be blank")
    @Size(max = 100, message = "Surname should be at most 50 characters")
    private String surname;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email should be at most 100 characters")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, max = 100, message = "Password should be at least 8 and at most 255 characters")
    private String password;

    @NotBlank(message = "Public key cannot be blank")
    @Size(max = 2048)
    private String publicKey;
}

