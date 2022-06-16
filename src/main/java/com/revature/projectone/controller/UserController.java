package com.revature.projectone.controller;


import com.revature.projectone.annotations.Authenticate;
import com.revature.projectone.dao.UserDAO;
import com.revature.projectone.model.Item;
import com.revature.projectone.model.User;
import com.revature.projectone.services.ItemService;
import com.revature.projectone.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    User user;

    @Autowired
    UserService userService;
    @Autowired
    ItemService itemService;

    @Autowired
    UserDAO userDAO;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Authenticate
    @GetMapping("/all")
    public ResponseEntity<List<User>> findAll() {
        LOGGER.info("Retrieving list of users");
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        ResponseEntity responseEntity = null;
        if (userDAO.findByEmail(user.getEmail()) != null) {
            LOGGER.warn(user.getEmail() + " already exists!");
            return responseEntity = new ResponseEntity<String>(user.getEmail() + " already exists!", HttpStatus.CONFLICT);

        } else {
            LOGGER.info(user.getEmail() + " has successfully been registered!");
            userDAO.save(user);// Must Implement addUser in userService for SRP
            return responseEntity = new ResponseEntity<String>(user.getEmail() + " has successfully been registered", HttpStatus.ACCEPTED);
        }

    }
    @Authenticate
    @PutMapping("cart/add")
    public ResponseEntity<String> addToCart(@RequestBody Item item){//Quantity functionality?
        ResponseEntity responseEntity = null;
        if(itemService.isItemExists(item.getItemId())){
            userService.addItemToCart(item.getItemId());
            LOGGER.info("Adding item " + item.getItemId() + "to cart!");
            return responseEntity = new ResponseEntity<String>(item.getItemId() + " successfully added to cart", HttpStatus.ACCEPTED);

        }else{
            LOGGER.warn("Item: "+ item.getItemId() + " not found, please add an existing item.");
            return responseEntity = new ResponseEntity<String>(item.getItemId() + " not found, please add an existing item", HttpStatus.NOT_FOUND);
        }

    }
    @Authenticate
    @PostMapping("order/add")
    public ResponseEntity<String> addToOrder(){
        userService.addCartToOrder();
        return new ResponseEntity<String>("Order has been placed! Thank you for your business!",HttpStatus.ACCEPTED);

    }


}
