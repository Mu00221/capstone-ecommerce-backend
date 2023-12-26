package com.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.model.Cart;
import com.shop.repository.CartRepo;
import com.shop.service.CartService;

@RestController
@CrossOrigin("*")
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartRepo cartRepo;

    // @GetMapping("/userCart/{userId}")
    // public Cart setUserId ( @PathVariable Long userId) throws Exception {
    //     return cartService.setUserId(userId);
    
    // }
    @GetMapping("/cartById/ {cartId}")
    public Cart getCartById(@PathVariable Long cartId) {
        Cart cart = cartRepo.findById(cartId).get();
        return cart;
    }

    @GetMapping("/getUserCart/{userId}")
    public Cart cartOfUser (@PathVariable Long userId) throws Exception {
        return cartService.cartOfUser(userId);
    }

    @GetMapping("/all") 
    public List<Cart> cartList () {
        return cartRepo.findAll();
    }
    
}
