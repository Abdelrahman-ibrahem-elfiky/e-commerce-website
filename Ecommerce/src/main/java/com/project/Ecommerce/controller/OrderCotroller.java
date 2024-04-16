package com.project.Ecommerce.controller;

import com.project.Ecommerce.exception.OrderException;
import com.project.Ecommerce.exception.UserException;
import com.project.Ecommerce.model.Address;
import com.project.Ecommerce.model.Orders;
import com.project.Ecommerce.model.User;
import com.project.Ecommerce.service.basicService.OrderService;
import com.project.Ecommerce.service.basicService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderCotroller {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;


    @PostMapping("/")
    public ResponseEntity<Orders>createOrder(@RequestBody Address shippingAddress,@RequestHeader("Authorization") String jwt)throws UserException, OrderException
    {
        User user=userService.findUserProfileByJwt(jwt);
        Orders order=orderService.createOrder(user,shippingAddress);

        return new ResponseEntity<Orders>(order, HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Orders>>userOrdersHistory(@RequestHeader("Authorization") String jwt)throws UserException
    {
        User user=userService.findUserProfileByJwt(jwt);
        List<Orders>orders=orderService.usersOrderHistory(user.getId());

        return new ResponseEntity<List<Orders>>(orders,HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Orders>findOrderById(@PathVariable("id") Long orderId,@RequestHeader("Authorization") String jwt)throws UserException,OrderException
    {
        User user=userService.findUserProfileByJwt(jwt);
        Orders order=orderService.findOrderByld(orderId);

        return new ResponseEntity<>(order,HttpStatus.ACCEPTED);
    }


}
