package com.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shop.model.Orders;

@Repository
public interface OrdersRepo extends JpaRepository<Orders, Long>{
    
        @Query(value = "SELECT p.type, p.name, p.details, p.price, pic_one  FROM \n" + //
            "product p JOIN orders o\n" + //
            "ON p.product_id = o.user_id   WHERE user_id = ?1", nativeQuery = true)
         List<Object[]> findProductAndUserInfo ( Long userId);
}
