package com.shop.model;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "phone")
    private int phone;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "points")
    private int points;

    @Column(name = "agent")
    private boolean agent;

    @Column(name = "code")
    private String code;

    @Column(name = "admin")
    private boolean admin;

    private String photo;

    // each user on cart.
   @JsonIgnore
   @OneToOne(mappedBy = "user")
   private Cart cart;

   // the user will be able to buy multiple products.
   @JsonIgnore
   @OneToMany(mappedBy = "users")
   private List<Orders> user = new ArrayList<>();
    
    
   
}
