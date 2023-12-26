package com.shop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.model.Cart;
import com.shop.model.User;
import com.shop.repository.CartRepo;
import com.shop.repository.UserRepo;

@Service
public class CartService {
    
    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private UserRepo userRepo;

    public Cart setUserId( Long userId) throws Exception {

      User user = userRepo.findById(userId).get();
      
      Cart cart = new Cart();
      if (user != null) {
        cart.setUser(user);
        cartRepo.save(cart);
        return cart;
      }else {
        throw new RuntimeException("The user is not found!");
      }

    }

    public Cart cartOfUser(Long userId) throws Exception {
        Optional<User> user = userRepo.findById(userId);
        if(user.isPresent()) {
          return cartRepo.userCart(userId);
        }else{
          throw new RuntimeException("the user cart is not found!");
        }
    }
}
