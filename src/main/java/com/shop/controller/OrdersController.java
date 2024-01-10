package com.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.model.Orders;
import com.shop.repository.OrdersRepo;
import com.shop.service.OrdersService;

@RestController
@CrossOrigin("*")
@RequestMapping("/orders")
public class OrdersController {
    
        @Autowired
        private OrdersService ordersService;

        @Autowired
        private OrdersRepo ordersRepo;

        // the method allow users to purchase and set the product id(sold) and user id(bought) to order table
        @PostMapping("/productO/{productId}/userO/{userId}")
        public Orders purchasProduct (@PathVariable Long productId, @PathVariable Long userId) {
            return  ordersService.purchasProduct( productId, userId);
        }

        // the method provide the list of all the transactions including user info and product info 
        // I used a cool query into order repository to get this result
        @GetMapping("/myTransactions/{userId}")
        public List<Object[]> userTransactions (@PathVariable Long userId) {
            return ordersRepo.findProductAndUserInfo(userId);
        } 
}


