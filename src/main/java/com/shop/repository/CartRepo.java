package com.shop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.shop.model.Cart;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {
    
    @Query(value = "SELECT * FROM shop.cart WHERE user_id = ?1", nativeQuery = true)
    Cart userCart (Long userId);
}
