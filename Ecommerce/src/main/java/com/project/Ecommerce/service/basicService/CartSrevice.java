package com.project.Ecommerce.service.basicService;

import com.project.Ecommerce.exception.ProductException;
import com.project.Ecommerce.model.Cart;
import com.project.Ecommerce.model.User;
import com.project.Ecommerce.request.AddItemRequest;

public interface CartSrevice {

    public Cart createCart(User user);
    public String addCartItem(Long userId, AddItemRequest req)throws ProductException;
    public Cart findUserCart(Long userId);
}
