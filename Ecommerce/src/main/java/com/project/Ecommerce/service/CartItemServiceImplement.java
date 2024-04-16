package com.project.Ecommerce.service;

import com.project.Ecommerce.exception.CartItemException;
import com.project.Ecommerce.exception.UserException;
import com.project.Ecommerce.model.Cart;
import com.project.Ecommerce.model.CartItem;
import com.project.Ecommerce.model.Product;
import com.project.Ecommerce.model.User;
import com.project.Ecommerce.repository.CartItemRepository;
import com.project.Ecommerce.repository.CartRepository;
import com.project.Ecommerce.service.basicService.CartItemService;
import com.project.Ecommerce.service.basicService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemServiceImplement implements CartItemService {

@Autowired
    private CartItemRepository cartItemRepository;
@Autowired
    private CartRepository cartRepository;
    private UserService userService;

    public CartItemServiceImplement(CartItemRepository cartItemRepository, CartRepository cartRepository, UserService userService) {
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
        this.userService = userService;
    }

    @Override
    public CartItem createCartItem(CartItem cartitem) {
        CartItem item=new CartItem();
        item.setQuantity(cartitem.getQuantity());
        item.setPrice(cartitem.getProduct().getPrice()*cartitem.getQuantity());
        item.setDiscountedPrice(cartitem.getProduct().getDiscountedPrice()*cartitem.getQuantity());
        item.setSize(cartitem.getSize());
        item.setProduct(cartitem.getProduct());
        item.setUserld(cartitem.getUserld());
        item.setCart(cartitem.getCart());
        CartItem createdCartItem=cartItemRepository.save(item);
        return createdCartItem;

    }

    @Override
    public CartItem updateCartItem(Long userld, Long id, CartItem cartItem) throws CartItemException, UserException {
       //CartItem item=findCartItemById(cartItem.getId());
       CartItem item=findCartItemById(id);
       User user=userService.findUserById(item.getUserld());
       User reqUser=userService.findUserById(userld);

       if (user.getId().equals(reqUser.getId()))
       {
           item.setQuantity(cartItem.getQuantity());
           item.setPrice(cartItem.getProduct().getPrice()*cartItem.getQuantity());
           item.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice()*cartItem.getDiscountedPrice());
           return cartItemRepository.save(item);
       }
       else
       {
           throw new UserException("you can't update another users item...");

       }
    }

    @Override
    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userld) {
        CartItem cartItem=cartItemRepository.isCartItemExist(cart,product,size,userld);
        return cartItem;
    }

    @Override
    public void removeCartItem(Long userld, Long cartitemld) throws CartItemException, UserException {

        CartItem item=findCartItemById(cartitemld);
        User user=userService.findUserById(item.getUserld());
        User reqUser=userService.findUserById(userld);
        if (user.getId().equals(reqUser.getId()))
        {
            cartItemRepository.deleteById(cartitemld);
        }
        else
        {
            throw new UserException("you can't remove another users item...");

        }


    }

    @Override
    public CartItem findCartItemById(Long cartitemid) throws CartItemException {

        Optional<CartItem>opt=cartItemRepository.findById(cartitemid);
        if (opt.isPresent())
        {
            return opt.get();
        }

        throw new CartItemException("CartItem Not Found With Id..."+cartitemid);

    }
}
