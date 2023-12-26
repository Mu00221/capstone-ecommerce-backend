package com.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.shop.model.Cart;import com.shop.model.Product;
import com.shop.repository.CartRepo;
import com.shop.repository.ProductRepo;


@Service
public class ProductService {
    
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CartRepo cartRepo;


    //  public Product purchasProduct(Long productId, Long userId) {

    //     User user = userRepo.findById(userId).get();
    //     Product product = productRepo.findById(productId).get();
        

    //      if(user != null) {
           
    //         product.setUserPro(user);
            
    //         int userPoints = user.getPoints();

    //         if(userPoints == 60) {
    //             product.setPrice(product.getPrice() * 0.8);
    //         }

    //         user.setPoints(user.getPoints() + 20);

    //      } 
    //       if( product.getUserPro() != null) {

    //         product.setQuantity(product.getQuantity() - 1);
    //         product.setSoldQty(product.getSoldQty() + 1);

    //         if(product.getQuantity() == 0) {
    //             product.setSold(true);
    //         }

    //      }

    //      productRepo.save(product);

    //     return product;
    // }

    


     public Product cartProduct(Long productId, Long userId) {

        
        Product product = productRepo.findById(productId).get();
        Cart cart = cartRepo.findById(userId).get();

         if(cart != null) {
            product.setMyCart(cart);
            productRepo.save(product);
         }

        return product;
    }

    

 

       
    // }

    //  public List<Product> getUserProducts(Long userId) throws Exception {
        
    //   Optional<User> user = userRepo.findById(userId);

    //     if(user.isPresent()) {
    //         return productRepo.listUserProduct(userId);
    //     }else {
    //         throw new AccessException("No product was found!");
    //     }
        
    // }

    

    public Product insertProduct(Product product) {
        Product addProduct = productRepo.save(product);
        return addProduct;
    }

    public List<Product> allProducts() {

        List<Product> products = productRepo.findBySoldFalse();
        return products;
    }

    public List<Product> getByGender(String gender) {

        return productRepo.getByGender(gender);
    }

    public Optional<Product> findByProductId(Long productId) {
       Optional<Product> product = productRepo.findById(productId);
       return product;
    }

    public List<Product> findByType(String type) {
        return productRepo.findByType(type);
    }

    public Product updateProducts(Product product, Long productId) {

        Optional<Product> productById = productRepo.findById(productId);

        if (productById.isPresent()) {
            Product prod = productById.get();
            prod.setGender(product.getGender());
            prod.setType(product.getType());
            prod.setColor(product.getColor());
            prod.setSize(product.getSize());
            prod.setMaterial(product.getMaterial());
            prod.setQuantity(product.getQuantity());
            prod.setPrice(product.getPrice());
            return productRepo.save(prod);
    
        } else {
            throw new ResourceAccessException("The product not found with id:" + productId);
        }
    }

    public void deleteProperty(Long productId) {
        Optional<Product> product = productRepo.findById(productId);

        if (product.isPresent()) {
            product.get().setMyCart(null);
            // product.get().setUserPro(null);
            productRepo.deleteById(productId);
        }
    }

    public List<Product> getCartProducts(Long cartId) throws Exception {
        
        Optional<Cart> cart = cartRepo.findById(cartId);
        if(cart.isPresent()) {
    
            return productRepo.listCartProducts(cartId);
        }else{
            throw new ResourceAccessException("the cart Id is not existed!");
        }
        
    }

    public Product removeProduct( Long productId) {
         Optional<Product> cartProduct = productRepo.findById(productId);

         if (cartProduct.isPresent()) {
            Product prod = cartProduct.get();
            prod.setMyCart(null);
            return productRepo.save(prod);
    
        } else {
            throw new ResourceAccessException("The product is not found for product_Id:" + productId);
        }
    }


   







}
