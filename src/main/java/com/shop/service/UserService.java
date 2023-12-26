package com.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.shop.model.Cart;
import com.shop.model.User;
import com.shop.repository.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;


    public List<User> listUser() {
        return userRepo.findAll();
    }

    public User signUp(User user) throws Exception {

        return userRepo.save(user);
    }

    public User signIn(User user) throws Exception {

        User getUser = userRepo.findByEmail(user.getEmail());

        if (getUser == null) {
            throw new Exception("Please enter a user");
        }

        if (!getUser.getEmail().equals(user.getEmail())
                || !getUser.getPassword().equals(user.getPassword())) {
            throw new Exception("Invalid email or password!");
        }
        return getUser;
    }

    public User getById(Long userId) throws Exception {
        Optional<User> user = userRepo.findById(userId);

        if (user.isPresent()) {
            return user.get();
        } else {
            throw new Exception("User not found");
        }
    }

    public User getByEmail(String email) throws Exception {
        User user = userRepo.findByEmail(email);

        if (user == null) {
            throw new Exception("User not found");
        }

        return user;
    }

    public User updateUser(User user, Long userId) {

        Optional<User> userI = userRepo.findById(userId);

        if (userI.isPresent()) {
            User use = userI.get();
            use.setFirstName(user.getFirstName());
            use.setLastName(user.getLastName());
            use.setEmail(user.getEmail());
            use.setPassword(user.getPassword());
            use.setDob(user.getDob());
            use.setPhone(user.getPhone());
            use.setAddress(user.getAddress());
            use.setCity(user.getCity());
            use.setState(user.getState());
            use.setPoints(user.getPoints());
            use.setAdmin(user.isAdmin());
            use.setAgent(user.isAgent());

           
            return userRepo.save(use);
        } else {
            throw new ResourceAccessException("The user not found" + userId);
        }

    }

    public void deleteUser(Long userId) {

        Optional<User> user = userRepo.findById(userId);
        
            Cart temp = user.get().getCart();
             temp.setUser(null);
            // Product product = new Product();
         if (user.isPresent()) {
            // product.setUserPro(null);
            userRepo.save(user.get());

            userRepo.deleteById(user.get().getUserId());
        }
    }

    public Optional<User> userStatus(Long userId) {

        Optional<User> myUser = userRepo.findById(userId);
        
        if(myUser.isPresent() && myUser.get().getCode().equals("admin") ){
           myUser.get().setAgent(true);  
        }
        if( myUser.isPresent() &&  myUser.get().getCode().equals("owner")) {
             myUser.get().setAdmin(true);
        }

        userRepo.save(myUser.get());

         


        return myUser;
    }

    

    

}
