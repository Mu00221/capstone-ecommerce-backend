package com.shop.model;




import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "product")
public class Product {


    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long productId;


    private String gender;

    private String type;

    private String name;

    private String color;

    private String size;

    private String material;

    private int quantity;

    private int soldQty;

    private double price;  

    private boolean sold;

    private String picOne;

    private String picTwo;

    private String picThree;

    private String picFour;

    private String review;

    private String shipping;

    private LocalDate purchaseDate;

    private String description;

    private String details;


    private String returnPolicy;




    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", referencedColumnName = "cartId")
    private Cart myCart;

    @JsonIgnore
   @OneToMany(mappedBy = "products")
   private List<Orders> product = new ArrayList<>();

   
}

