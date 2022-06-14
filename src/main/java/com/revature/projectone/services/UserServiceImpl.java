package com.revature.projectone.services;

import com.revature.projectone.dao.CartDAO;
import com.revature.projectone.dao.ItemDAO;
import com.revature.projectone.dao.OrderDAO;
import com.revature.projectone.dao.UserDAO;
import com.revature.projectone.model.Cart;
import com.revature.projectone.model.Item;
import com.revature.projectone.model.Order;
import com.revature.projectone.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{


    @Autowired
    private HttpServletRequest req;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ItemService itemService;

    @Autowired
    private CartDAO cartDAO;

    @Autowired
    private OrderDAO orderDAO;


    public static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);


    @Override
    public void register(User user) {
        userDAO.save(user);
     }

    @Override
    public User login(String email, String password)  {
        User user = userDAO.findByEmailAndPassword(email, password);
        HttpSession session = req.getSession();
        session.setAttribute("currentUser", user);
        return user;
    }

    @Override
    public void logout() {
        HttpSession session = req.getSession(false);

        if(session == null) {
            // No one was logged in
LOGGER.warn("No one is logged in");
            return;
        }
LOGGER.info("Successfully logged out");
        session.invalidate();
    }

    @Override
    public User getByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    public User findByEmailAndPassword(String email, String password) {return userDAO.findByEmailAndPassword(email, password);}


    @Override
    public List<User> getUsers() {
        return userDAO.findAll();
    }

    @Override
    public boolean isUserExists(int userId) {
     return userDAO.existsById(userId);
    }

    @Override
    public void addItemToCart(int itemId) {
        HttpSession session = req.getSession(false);
        LOGGER.debug("Getting current user cart");
        User user = (User) session.getAttribute("currentUser");
        Cart cart = user.getCart();
        LOGGER.debug("Pulling list from current cart of user");
        List<Item> cartList = cart.getCartList();
        cartList.add(itemService.getItem(itemId));
        cart.setCartList(cartList);
        cartDAO.save(cart);
        userDAO.save(user);


    }

    @Override
    public void addCartToOrder() {
        HttpSession session = req.getSession(false);//get session object
        User user = (User) session.getAttribute("currentUser");//get user of current session casting the information returned to an user type object
        Cart cart = user.getCart();//gets the cart of current user
        Order order = new Order();//create a new order object
        List<Item> cartList = cart.getCartList();//get current user cartlist
        order.setOrderList(cartList);//set list info from cartlist as the list from the order object
        orderDAO.save(order);//save information on order object including orderlist property
        cartList.clear();//clear cartlist collection
        cartDAO.save(cart);//save now empty cart
        userDAO.save(user);// save user info with empty cartlist


    }

    @Override
    public List<Item> getCartItems() {
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("currentUser");
        Cart cart = user.getCart();
        return cart.getCartList();
    }
}
