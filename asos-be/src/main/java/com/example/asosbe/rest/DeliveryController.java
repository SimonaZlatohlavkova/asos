package com.example.asosbe.rest;

import com.example.asosbe.dto.DeliveryResponse;
import com.example.asosbe.exception.ErrorResponse;
import com.example.asosbe.service.IDeliveryService;
import com.example.asosbe.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.LoginException;
import java.util.List;

@RestController
@RequestMapping("/delivery")
@AllArgsConstructor
public class DeliveryController {

    private IDeliveryService iDeliveryService;

    private final UserService userService;

    @GetMapping()
    public ResponseEntity<Object> getDelivery(@RequestHeader("Authorization") String jwt) {
        try{
            userService.getUserIdByToken(jwt);
        }
        catch(LoginException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse(e.getMessage(),
                    HttpStatus.UNAUTHORIZED.value(), e.getClass().getName()));
        }
        return ResponseEntity.ok(this.iDeliveryService.getDeliveries());
    }
}
