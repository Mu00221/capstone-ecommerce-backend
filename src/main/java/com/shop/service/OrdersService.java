package com.shop.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.model.Orders;
import com.shop.model.Product;
import com.shop.model.User;
import com.shop.repository.OrdersRepo;
import com.shop.repository.ProductRepo;
import com.shop.repository.UserRepo;


@Service
public class OrdersService {
    
    @Autowired
    private OrdersRepo ordersRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ProductRepo productRepo;


    public Orders purchasProduct(Long productId, Long userId) {
        User user = userRepo.findById(userId).get();
        Product product = productRepo.findById(productId).get();

        Orders purchase = new Orders();
        if(user != null) {
        purchase.setUsers(user);
        purchase.setProducts(product);
        purchase.setOrderDate(LocalDate.now());

            int userPoints = user.getPoints();

            if(userPoints == 60) {
                product.setPrice(product.getPrice() * 0.8);
            }

            user.setPoints(user.getPoints() + 20);
        }

        if( purchase.getUsers() != null) {

                    product.setQuantity(product.getQuantity() - 1);
                    product.setSoldQty(product.getSoldQty() + 1);
        
                    if(product.getQuantity() == 0) {
                        product.setSold(true);
                    }
        
                 }
        ordersRepo.save(purchase);

        return purchase;
    }

   
}
