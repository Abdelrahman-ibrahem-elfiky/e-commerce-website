package com.project.Ecommerce.controller;

import com.project.Ecommerce.exception.UserException;
import com.project.Ecommerce.model.User;
import com.project.Ecommerce.request.LoginRequest;
import com.project.Ecommerce.response.AuthResponse;
import com.project.Ecommerce.service.CustomerServiceImplement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private CustomerServiceImplement customerServiceImplement;

    public AuthController(CustomerServiceImplement customerServiceImplement) {
        this.customerServiceImplement = customerServiceImplement;
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse>register(@RequestBody User user) throws UserException{

        return customerServiceImplement.createdUserHandler(user);
    }
    @PostMapping("/signin")
    public ResponseEntity<AuthResponse>login(@RequestBody LoginRequest loginRequest){

        return customerServiceImplement.loginUser(loginRequest);
    }
}
