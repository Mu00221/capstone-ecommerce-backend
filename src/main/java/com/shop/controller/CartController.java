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

    // dependency injection 
    // this allow me to access the properties and the codes from the class that i injected
    @Autowired 
    private CartService cartService;

    @Autowired
    private CartRepo cartRepo;

    // one of the CRUD opperation which help the client to fetch data from our endpoints
    @GetMapping("/cartById/ {cartId}")
                            // patvariable serialize and deserialize the method params
    public Cart getCartById(@PathVariable Long cartId) {
        Cart cart = cartRepo.findById(cartId).get();
        return cart;
    }

    // this mehtod allow us to find the cart id of a user.
    @GetMapping("/getUserCart/{userId}")
    public Cart cartOfUser (@PathVariable Long userId) throws Exception {
        return cartService.cartOfUser(userId);
    }

    //List of all the cart that exists
    @GetMapping("/all") 
    public List<Cart> cartList () {
        return cartRepo.findAll();
    }
    
}
