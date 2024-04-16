package com.project.Ecommerce.service;

import com.project.Ecommerce.model.OrderItem;
import com.project.Ecommerce.repository.OrdreItemRepository;
import com.project.Ecommerce.service.basicService.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImplement implements OrderItemService {

    @Autowired
   private OrdreItemRepository ordreItemRepository;

    public OrderItemServiceImplement(OrdreItemRepository ordreItemRepository) {
        this.ordreItemRepository = ordreItemRepository;
    }

    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        return ordreItemRepository.save(orderItem);
    }
}
