package com.revature.projectone.controller;

import com.revature.projectone.dao.UserDAO;
import com.revature.projectone.exceptions.UserNotFoundException;
import com.revature.projectone.model.LoginTemplate;
import com.revature.projectone.model.User;
import com.revature.projectone.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDAO userDAO;

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);


    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginTemplate loginTemplate) {

        LOGGER.info("Attempting to login " + loginTemplate.getEmail() + "!");
        //check if login info exists

        String email = loginTemplate.getEmail();
        if (userService.findByEmailAndPassword(loginTemplate.getEmail(), loginTemplate.getPassword()) != null) {
            return ResponseEntity.ok(userService.login(loginTemplate.getEmail(), loginTemplate.getPassword()));
        } else {
            throw new UserNotFoundException("Invalid username or password" + ResponseEntity.badRequest().build());
        }




    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        ResponseEntity responseEntity = null;
        userService.logout();
        LOGGER.info("You have successfully logged out");
        return responseEntity = new ResponseEntity<String>("You have been successfully logged out!", HttpStatus.ACCEPTED);
    }

}
