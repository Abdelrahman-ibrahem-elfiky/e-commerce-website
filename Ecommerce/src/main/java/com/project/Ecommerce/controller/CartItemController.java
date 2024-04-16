package com.project.Ecommerce.controller;

import com.project.Ecommerce.exception.CartItemException;
import com.project.Ecommerce.exception.UserException;
import com.project.Ecommerce.model.User;
import com.project.Ecommerce.repository.CartRepository;
import com.project.Ecommerce.response.ApiResponse;
import com.project.Ecommerce.service.basicService.CartItemService;
import com.project.Ecommerce.service.basicService.CartSrevice;
import com.project.Ecommerce.service.basicService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cartitem")
public class CartItemController {

    @Autowired
    private CartSrevice cartSrevice;
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private UserService userService;

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<ApiResponse>deleteCartItem(@RequestHeader("Authorization") String jwt, @PathVariable Long cartItemId)throws UserException, CartItemException
    {

        User user=userService.findUserProfileByJwt(jwt);
        cartItemService.removeCartItem(user.getId(), cartItemId);

        ApiResponse response=new ApiResponse();
        response.setMessage("Delete Item From Cart");
        response.setStatus(true);
        return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);



    }


}
