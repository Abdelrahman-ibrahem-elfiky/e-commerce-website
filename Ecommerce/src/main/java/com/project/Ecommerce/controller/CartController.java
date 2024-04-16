package com.project.Ecommerce.controller;

import com.project.Ecommerce.exception.ProductException;
import com.project.Ecommerce.exception.UserException;
import com.project.Ecommerce.model.Cart;
import com.project.Ecommerce.model.CartItem;
import com.project.Ecommerce.model.User;
import com.project.Ecommerce.request.AddItemRequest;
import com.project.Ecommerce.response.ApiResponse;
import com.project.Ecommerce.service.basicService.CartSrevice;
import com.project.Ecommerce.service.basicService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/cart")

public class CartController {

    @Autowired
    private CartSrevice cartSrevice;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<Cart>findUserCart(@RequestHeader("Authorization") String jwt)throws UserException
    {
        User user=userService.findUserProfileByJwt(jwt);
        Cart cart=cartSrevice.findUserCart(user.getId());
        System.out.println(cart.getCartitems().size());

       return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }


    @PutMapping("/add")
    public ResponseEntity<ApiResponse>addItemToCart(@RequestHeader("Authorization") String jwt, @RequestBody AddItemRequest request)throws UserException , ProductException
    {
        User user=userService.findUserProfileByJwt(jwt);
        cartSrevice.addCartItem(user.getId(),request);

      ApiResponse response=new ApiResponse();
      response.setMessage("Item added To Cart");
      response.setStatus(true);

        return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
    }
}
