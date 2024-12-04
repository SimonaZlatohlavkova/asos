package com.example.asosbe.rest;

import com.example.asosbe.dto.OrderRequest;
import com.example.asosbe.exception.ErrorResponse;
import com.example.asosbe.service.IOrderService;
import com.example.asosbe.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {

    private IOrderService iOrderService;

    private final IUserService userService;
    @PostMapping()
    public ResponseEntity<Object> createOrder(@RequestBody OrderRequest orderRequest,
                                              @RequestHeader("Authorization") String jwt) {
        Long userId;
        try{
            userId = userService.getUserIdByToken(jwt);
        }
        catch(LoginException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse(e.getMessage(),
                    HttpStatus.UNAUTHORIZED.value(), e.getClass().getName()));
        }
        var order = iOrderService.createOrder(orderRequest, userService.getById(userId));
        return ResponseEntity.ok().body(order);
    }
}
