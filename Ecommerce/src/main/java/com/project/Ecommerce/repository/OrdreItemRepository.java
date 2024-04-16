package com.project.Ecommerce.repository;

import com.project.Ecommerce.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdreItemRepository extends JpaRepository<OrderItem,Long> {


}
