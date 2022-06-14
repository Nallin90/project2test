package com.revature.projectone.services;

import com.revature.projectone.dao.UserDAO;
import com.revature.projectone.model.Item;
import com.revature.projectone.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;






public interface UserService {


    public void register(User user);
    public User login(String email, String password);

    public void logout();

    public User getByEmail(String email);

    public User findByEmailAndPassword(String email, String password);

    public List<User> getUsers();

    public boolean isUserExists(int userId);

    public void addItemToCart(int itemId);

    public void addCartToOrder();

    public List<Item> getCartItems();
}



