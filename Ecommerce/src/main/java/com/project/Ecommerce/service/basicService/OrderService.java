package com.project.Ecommerce.service.basicService;

import com.project.Ecommerce.exception.OrderException;
import com.project.Ecommerce.model.Address;
import com.project.Ecommerce.model.Orders;
import com.project.Ecommerce.model.User;

import java.util.List;

public interface OrderService {

    public Orders createOrder(User user, Address shippingAdress);
    public Orders findOrderByld (Long orderld) throws OrderException;
    public List<Orders> usersOrderHistory(Long userld);
    public Orders placedOrder(Long orderld) throws OrderException;
    public Orders confirmedOrder(Long orderld)throws OrderException;
    public Orders shippedOrder(Long orderld) throws OrderException;
    public Orders deliveredOrder(Long orderld) throws OrderException;
    public Orders cancledOrder(Long orderld) throws OrderException;

    public List<Orders> getAllOreder();
    public String deleteOrder(Long orderId) throws OrderException;


}
