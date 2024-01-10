package com.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shop.model.Product;
import com.shop.enums.ProductType;
import com.shop.enums.GenderType;



@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    
    List<Product> findBySoldFalse();

    List<Product> getByGender(String gender);

    List<Product> findByType(String type);

    
    //     @Query(value = "SELECT p.user_id, first_name, last_name, p.product_id, type, color  FROM \n" + //
    //         "product p JOIN user u\n" + //
    //         "ON p.user_id = u.user_id", nativeQuery = true)
    //    List<Object[]> findProductAndUserInfo ();



     @Query(value = "SELECT * FROM shop.product WHERE user_id = ?1", nativeQuery = true )
    List<Product> listUserProduct (Long orderId);

    @Query(value = "SELECT * FROM shop.product WHERE cart_id = ?1", nativeQuery = true)
    List<Product> listCartProducts (Long cartId);

    List<Product> findByType(ProductType type);

    List<Product> findByGender(GenderType gender);

    List<Product> findByTypeAndGender(ProductType type, GenderType gender);

    @Query(value = "SELECT * FROM shop.product WHERE type = \"hoodie\" ", nativeQuery = true)
    List<Product> listHoodies ();

    @Query(value = "SELECT * FROM shop.product WHERE type = \"t-shirt\" ", nativeQuery = true)
    List<Product> listTshirts ();

    @Query(value = "SELECT * FROM shop.product WHERE type = \"pant\" ", nativeQuery = true)
    List<Product> listPnats ();

    @Query(value = "SELECT * FROM shop.product WHERE type = \"jean\"  ", nativeQuery = true)
    List<Product> listJeans ();

    @Query(value = "SELECT * FROM shop.product WHERE type = \"coat\" ", nativeQuery = true)
    List<Product> listCoats ();
    
    @Query(value = "SELECT * FROM shop.product WHERE type = \"dresses\" ", nativeQuery = true)
    List<Product> listDresses ();

    @Query(value = "SELECT * FROM shop.product WHERE type = \"blazer\" ", nativeQuery = true)
    List<Product> listBlazer ();

    @Query(value = "SELECT * FROM shop.product WHERE type = \"activewear\" ", nativeQuery = true)
    List<Product> listActivewear ();

    @Query(value = "SELECT * FROM shop.product WHERE type = \"outerwear\" ", nativeQuery = true)
    List<Product> listOuterwear ();

   

    
}
