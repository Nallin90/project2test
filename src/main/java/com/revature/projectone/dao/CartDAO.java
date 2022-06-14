package com.revature.projectone.dao;

import com.revature.projectone.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartDAO extends JpaRepository<Cart, Integer> {

//public void insertToCart(int itemId);

}
