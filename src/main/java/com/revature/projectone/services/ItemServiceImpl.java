package com.revature.projectone.services;

import com.revature.projectone.dao.ItemDAO;
import com.revature.projectone.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemDAO itemDAO;

    @Override
    public List<Item> getItems() {
        return itemDAO.findAll();
    }

    @Override
    public boolean addItem(Item item) {
        itemDAO.save(item);
        return true;
    }

    @Override
    public Item getItem(int itemId) {
        return itemDAO.getReferenceById(itemId);
    }

    @Override
    public List<Item> getItem(String itemName) {
        return itemDAO.getItemByName(itemName);
    }

    @Override
    public Boolean isItemExists(Integer itemId) {
        return itemDAO.existsById(itemId);
    }
}
