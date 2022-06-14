package com.revature.projectone.dao;

import com.revature.projectone.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemDAO extends JpaRepository<Item, Integer> {

@Query("select i from Item i where itemName = ?1")
    List<Item> getItemByName(String itemName);
}
