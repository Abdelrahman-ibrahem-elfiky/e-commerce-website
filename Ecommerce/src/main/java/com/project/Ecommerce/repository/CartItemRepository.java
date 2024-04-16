package com.project.Ecommerce.repository;

import com.project.Ecommerce.model.Cart;
import com.project.Ecommerce.model.CartItem;
import com.project.Ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    @Query("select ci from CartItem ci where ci.cart=:cart and ci.product=:product and ci.size=:size and ci.userld=:userId")
    public CartItem isCartItemExist(
            @Param("cart")Cart cart,
            @Param("product")Product product,
            @Param("size") String size,
            @Param("userId")Long userId);
}
