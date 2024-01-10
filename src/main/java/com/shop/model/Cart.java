package com.shop.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data // data is from lombok dependency which generate setter, getters and some other useful stuffs.
@Entity // this annotarion defines that the class is an entity.
@Table(name = "cart") // create table in our database.
public class Cart {


    // This is the cart table where users can save their products and purchas later.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // the code above generate the id for us. so every time the data is created it automatically hava an id.
    private Long cartId;

    // I defined one to one so each user can have only one cart.
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_Id", referencedColumnName = "userId") 
    private User user;

    // one to many make user have multiple items in her/his cart.
    @JsonIgnore
    @OneToMany(mappedBy = "myCart")
    private Set<Product> cartPro = new HashSet<>();
   
    
}
