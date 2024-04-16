package com.project.Ecommerce.controller;

import com.project.Ecommerce.exception.OrderException;
import com.project.Ecommerce.model.Orders;
import com.project.Ecommerce.service.basicService.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/orders")
public class AdminOrderController {

    @Autowired
    private OrderService orderService;


    @GetMapping("/")
    public ResponseEntity<List<Orders>>getAllOrdersHandler()
    {
        List<Orders>orders=orderService.getAllOreder();
        return new ResponseEntity<List<Orders>>(orders, HttpStatus.ACCEPTED);

    }

    @PutMapping("/{orderId}/confirmed")
    public ResponseEntity<Orders>ConfirmedOrderHandler(@PathVariable Long orderId, @RequestHeader("Authorization") String JWT) throws OrderException
    {
        Orders orders=orderService.confirmedOrder(orderId);
        return new ResponseEntity<Orders>(orders,HttpStatus.OK);
    }

    @PutMapping("/{orderId}/ship")
    public ResponseEntity<Orders>ShippedOrderHandler(@PathVariable Long orderId, @RequestHeader("Authorization") String JWT) throws OrderException
    {
        Orders orders=orderService.shippedOrder(orderId);
        return new ResponseEntity<Orders>(orders,HttpStatus.OK);
    }

    @PutMapping("/{orderId}/deliver")
    public ResponseEntity<Orders>DeliverOrderHandler(@PathVariable Long orderId, @RequestHeader("Authorization") String JWT) throws OrderException
    {
        Orders orders=orderService.deliveredOrder(orderId);
        return new ResponseEntity<Orders>(orders,HttpStatus.OK);
    }
    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<Orders>CancelOrderHandler(@PathVariable Long orderId, @RequestHeader("Authorization") String JWT) throws OrderException
    {
        Orders orders=orderService.cancledOrder(orderId);
        return new ResponseEntity<Orders>(orders,HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}/delete")
    public ResponseEntity<String>DeleteOrderHandler(@PathVariable Long orderId, @RequestHeader("Authorization") String JWT) throws OrderException
    {
        String state=orderService.deleteOrder(orderId);
        return new ResponseEntity<String>(state,HttpStatus.OK);
    }


}
