package com.project.Ecommerce.service;

import com.project.Ecommerce.exception.OrderException;
import com.project.Ecommerce.model.*;
import com.project.Ecommerce.repository.*;
import com.project.Ecommerce.service.basicService.CartSrevice;
import com.project.Ecommerce.service.basicService.OrderService;
import com.project.Ecommerce.service.basicService.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImplement implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrdreItemRepository ordreItemRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartSrevice cartSrevice;
    @Autowired
    private CartSrevice cartItemSrevice;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;


    @Override
    public Orders createOrder(User user, Address shippingAdress) {
        shippingAdress.setUser(user);
        Address address=addressRepository.save(shippingAdress);
        user.getAddress().add(address);
        userRepository.save(user);

        Cart cart=cartSrevice.findUserCart(user.getId());
        List<OrderItem>orderItems=new ArrayList<>();

        for (CartItem item:cart.getCartitems())
        {
            OrderItem orderItem=new OrderItem();
            orderItem.setPrice(item.getPrice());;
            orderItem.setProduct(item.getProduct());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setSize(item.getSize());
            orderItem.setUserld(item.getUserld());
            orderItem.setDiscountedPrice(item.getDiscountedPrice());

            OrderItem createdOrderItem=ordreItemRepository.save(orderItem);
            orderItems.add(createdOrderItem);
        }

        Orders createdOrder=new Orders();
        createdOrder.setUser(user);
        createdOrder.setOrderltems(orderItems);
        createdOrder.setTotalPrice(cart.getTotalPrice());
       // createdOrder.s(cart.getTotalDiscountedPrice());
        createdOrder.setDiscounte(cart.getDiscounte());
        createdOrder.setTotalItem(cart.getTotalltem());
        createdOrder.setShippingAddress(address);
        createdOrder.setOrderDate(LocalDateTime.now());
        createdOrder.setOrderStatus("PENDING");
        createdOrder.getPaymentDetails().setStatus("PENDING");
        createdOrder.setCreateAt(LocalDateTime.now());

        Orders savedOrder=orderRepository.save(createdOrder);

        for (OrderItem item:orderItems)
        {
            item.setOrders(savedOrder);
            ordreItemRepository.save(item);
        }

        return savedOrder;
    }

    @Override
    public Orders findOrderByld(Long orderld) throws OrderException {

        Optional<Orders>optionalOrders=orderRepository.findById(orderld);
        if (optionalOrders.isPresent())
        {
            return optionalOrders.get();
        }
        throw new OrderException("Order Not Exist With Id");

    }

    @Override
    public List<Orders> usersOrderHistory(Long userld) {
        List<Orders>orders= orderRepository.getUsersOrder(userld);
        return orders;
    }

    @Override
    public Orders placedOrder(Long orderld) throws OrderException {

        Orders order=findOrderByld(orderld);
        order.setOrderStatus("PLACED");
        order.getPaymentDetails().setStatus("COMPLETED");
        return order;
    }


    @Override
    public Orders confirmedOrder(Long orderld) throws OrderException {
        Orders order=findOrderByld(orderld);
        order.setOrderStatus("CONFIRMED");
        return orderRepository.save(order);
    }

@Override
public Orders shippedOrder(Long orderld) throws OrderException {
    Orders order = findOrderByld(orderld);
    order.setOrderStatus("SHIPPED");
    return orderRepository.save(order);
}



    @Override
    public Orders deliveredOrder(Long orderld) throws OrderException {
    Orders order=findOrderByld (orderld);
    order.setOrderStatus("DELIVERED");
    return orderRepository.save(order);
    }

    @Override
    public Orders cancledOrder(Long orderld) throws OrderException
    {
        Orders order=findOrderByld (orderld);
        order.setOrderStatus("CANCELLED");
        return orderRepository.save(order);
    }

    @Override
    public List<Orders> getAllOreder() {

        List<Orders>orders=orderRepository.getAllOrders();
        return orders;
    }

    @Override
    public String deleteOrder(Long orderId) throws OrderException {

        Optional<Orders>optionalOrders=orderRepository.findById(orderId);
        if (optionalOrders.isPresent())
        {
            orderRepository.deleteById(optionalOrders.get().getId());

            return "Order Deleted Successfully";
        }
        throw  new OrderException("this order not found");

    }
}
