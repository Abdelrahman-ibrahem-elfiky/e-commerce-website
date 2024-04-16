package com.project.Ecommerce.repository;

import com.project.Ecommerce.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Long> {

    @Query("select o from  Orders o where o.user.id=:userId and (o.orderStatus= 'PLACED' OR o.orderStatus= 'CONFIRMED' OR o.orderStatus= 'SHIPPED' OR o.orderStatus= 'DELIVERED')")
    public List<Orders> getUsersOrder(@Param("userId") Long userId);
    @Query("SELECT o FROM Orders o")
    public List<Orders>getAllOrders();
}
