package com.shop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shop.model.Product;
import com.shop.repository.ProductRepo;
import com.shop.service.ProductService;

@RestController
@CrossOrigin("*")
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepo productRepo;



    @GetMapping("/hoodie")
    public List<Product> getHoodies() {
        
        List<Product> hoodies = productRepo.listHoodies();
        return hoodies;
    }

    @GetMapping("/t-shirt")
    public List<Product> getTshirts() {
        
        List<Product> tshirt = productRepo.listTshirts();
        return tshirt;
    }

     @GetMapping("/pant")
    public List<Product> getPants() {
        
        List<Product> pant = productRepo.listPnats();
        return pant;
    }

     @GetMapping("/jean")
    public List<Product> getJeans() {
        
        List<Product> jean = productRepo.listJeans();
        return jean;
    }

    

     @GetMapping("/coat")
    public List<Product> getCoats() {
        
        List<Product> coat = productRepo.listCoats();
        return coat;
    }

     @GetMapping("/dresses")
    public List<Product> getDresses() {
        
        List<Product> dresses = productRepo.listDresses();
        return dresses;
    }




     @GetMapping("/blazer")
    public List<Product> getBlazer() {
        
        List<Product> blazer = productRepo.listBlazer();
        return blazer;
    }

     @GetMapping("/activewear")
    public List<Product> getActivewear() {
        
        List<Product> active = productRepo.listActivewear();
        return active;
    }


     @GetMapping("/outerwear")
    public List<Product> getOuterwear() {
        
        List<Product> outer = productRepo.listOuterwear();
        return outer;
    }


    // @PostMapping("/productO/{productId}/userO/{userId}")
    // public Product purchasProduct (@PathVariable Long productId, @PathVariable Long userId) {
    //   return  productService.purchasProduct( productId, userId);
    // }

     

     @PostMapping("/productO/{productId}/cartO/{cartId}")
    public Product cartProduct (@PathVariable Long productId, @PathVariable Long cartId) {
      return  productService.cartProduct( productId, cartId);
    }


    // @GetMapping("/userPro/{userId}")
    // public List<Product> loadUserProducts(@PathVariable Long userId) {
    //     List<Product> products = productService.loadUserProducts(userId);
    //     return products;
    // }

    // @GetMapping("/productOfUser/{userId}")
    // public List<Product> getUserProducts (@PathVariable Long userId) throws Exception {

    //     List<Product> products = productService.getUserProducts(userId);
    //     return products;
    // }

    @GetMapping("productOfCart/{cartId}")
    public List<Product> getCartProducts (@PathVariable Long cartId) throws Exception {
        return productService.getCartProducts(cartId);
    }

    @PostMapping("/add")
    public Product insertProduct(@RequestBody Product product) {
        Product addProduct = productService.insertProduct(product);
        return addProduct;
    }

    @GetMapping("/get")
    public List<Product> unSoldProducts() {

        List<Product> getProducts = productService.allProducts();
        return getProducts;
    }

    @GetMapping("/getByName")
    public ResponseEntity<Object> getByGender(@RequestParam("gender") String gender) {
        try {
            List<Product> foundProducts = productService.getByGender(gender);
            return new ResponseEntity<Object>(foundProducts, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Error e) {
            System.out.println(e);
            return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

       @GetMapping("/productById/{productId}")
    public ResponseEntity<Object> findByProductId(@PathVariable Long productId) {
        try {
            Optional<Product> foundProducts = productService.findByProductId(productId);
            return new ResponseEntity<Object>(foundProducts, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Error e) {
            System.out.println(e);
            return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<Object> findByType(@PathVariable String type) {
        try {
            List<Product> foundProducts = productService.findByType(type);
            return new ResponseEntity<Object>(foundProducts, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Error e) {
            System.out.println(e);
            return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<Object> udateProducts(@RequestBody Product product, @PathVariable Long productId) {

        try {

            Product updateProcess = productService.updateProducts(product, productId);
            return new ResponseEntity<>(updateProcess, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Error e) {
            System.out.println(e);
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long productId) {
        try {
            productService.deleteProperty(productId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Error e) {
            System.out.println(e);
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //  @GetMapping("/allTransaction")
    // public List<Object[]> viewAllTransactions() {

    //     return productRepo.findProductAndUserInfo();

        
    // }


     @PostMapping("/remove/{productId}")
    public ResponseEntity<Object> removeCartProduct( @PathVariable Long productId) {

        try {

            Product updateProcess = productService.removeProduct(productId);
            return new ResponseEntity<>(updateProcess, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Error e) {
            System.out.println(e);
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

   
    

   

}
