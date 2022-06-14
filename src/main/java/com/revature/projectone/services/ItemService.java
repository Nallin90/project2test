package com.revature.projectone.services;

import com.revature.projectone.model.Item;

import java.util.ArrayList;
import java.util.List;

public interface ItemService {

    public List<Item> getItems();

    public boolean addItem(Item item);

    public Item getItem(int itemId);
    public List<Item> getItem(String itemName);
    public Boolean isItemExists(Integer itemId);


}
