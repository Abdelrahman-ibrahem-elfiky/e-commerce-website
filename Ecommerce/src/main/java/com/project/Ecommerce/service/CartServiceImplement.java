package com.project.Ecommerce.service;

import com.project.Ecommerce.exception.ProductException;
import com.project.Ecommerce.model.Cart;
import com.project.Ecommerce.model.CartItem;
import com.project.Ecommerce.model.Product;
import com.project.Ecommerce.model.User;
import com.project.Ecommerce.repository.CartRepository;
import com.project.Ecommerce.request.AddItemRequest;
import com.project.Ecommerce.service.basicService.CartItemService;
import com.project.Ecommerce.service.basicService.CartSrevice;
import com.project.Ecommerce.service.basicService.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImplement implements CartSrevice {

    private CartRepository cartRepository;
    private CartItemService cartItemService;
    private ProductService productService;

    public CartServiceImplement(CartRepository cartRepository, CartItemService cartItemService, ProductService productService) {
        this.cartRepository = cartRepository;
        this.cartItemService = cartItemService;
        this.productService = productService;
    }

    @Override
    public Cart createCart(User user) {
        Cart cart=new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    @Override
    public String addCartItem(Long userId, AddItemRequest req) throws ProductException {
        Cart cart=cartRepository.findByUserId(userId);
        Product product=productService.findProductById(req.getProductId());

         CartItem isPersent=cartItemService.isCartItemExist(cart,product, req.getSize(), userId);

        if (isPersent==null)
        {
            CartItem cartItem=new CartItem();
            int price=req.getQuantity()*product.getDiscountedPrice();
            cartItem.setPrice(price);
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItem.setQuantity(req.getQuantity());
            cartItem.setUserld(userId);
            cartItem.setSize(req.getSize());

            CartItem createdItem=cartItemService.createCartItem(cartItem);
            cart.getCartitems().add(createdItem);
        }

        return "item add to cart ";
    }

    @Override
    public Cart findUserCart(Long userId) {

        Cart cart=cartRepository.findByUserId(userId);

        int totalPrice=0;
        int totalDiscountedPrice=0;
        int totalItem=0;

        for (CartItem item:cart.getCartitems())
        {
            totalPrice=totalPrice+item.getPrice();
            totalDiscountedPrice=totalDiscountedPrice+item.getDiscountedPrice();
            totalItem=totalItem+item.getQuantity();
        }
        cart.setTotalPrice(totalPrice);
        cart.setTotalDiscountedPrice(totalDiscountedPrice);
        cart.setTotalltem(totalItem);
        cart.setDiscounte(totalPrice-totalDiscountedPrice);

        return  cartRepository.save(cart);
    }
}
