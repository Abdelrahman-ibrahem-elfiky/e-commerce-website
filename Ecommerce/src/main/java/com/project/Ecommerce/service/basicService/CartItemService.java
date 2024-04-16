package com.project.Ecommerce.service.basicService;

import com.project.Ecommerce.exception.CartItemException;
import com.project.Ecommerce.exception.UserException;
import com.project.Ecommerce.model.Cart;
import com.project.Ecommerce.model.CartItem;
import com.project.Ecommerce.model.Product;

public interface CartItemService {

    public CartItem createCartItem(CartItem cartitem);
    public CartItem updateCartItem(Long userld, Long id, CartItem cartItem) throws CartItemException, UserException;
    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userld);
    public void removeCartItem(Long userld, Long cartitemld) throws CartItemException, UserException;
    public CartItem findCartItemById(Long cartitemid)throws CartItemException;
}
