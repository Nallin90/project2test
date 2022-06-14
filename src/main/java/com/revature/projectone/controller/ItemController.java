package com.revature.projectone.controller;

import com.revature.projectone.annotations.Authenticate;
import com.revature.projectone.model.Item;
import com.revature.projectone.services.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("shop")


public class ItemController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);


    @Autowired()
    Item item;

    @Autowired
    ItemService itemService;


    @GetMapping
    public ResponseEntity<List<Item>> getItems() {
        ResponseEntity responseEntity = null;
        LOGGER.info("Retrieving all items");
        List<Item> items = new ArrayList<Item>();
        items = itemService.getItems();
        return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
    }

    @GetMapping("{itemId}")
    public ResponseEntity<Item> getItem(@PathVariable("itemId") int itemId) {
        LOGGER.info("Retrieving item" + itemId + " details.");
        ResponseEntity responseEntity = null;
        Item item1 = new Item();
        //check to see if product exists
        if (itemService.isItemExists(itemId)) {
            LOGGER.info("Item" + itemId + " is found");
            item1 = itemService.getItem(itemId);
            responseEntity = new ResponseEntity<Item>(item1, HttpStatus.OK);
        } else {
            LOGGER.warn("Item" + itemId + " is not found please update database accordingly");
            responseEntity = new ResponseEntity<Item>(item1, HttpStatus.NO_CONTENT);
        }
        return responseEntity;

    }

    @Authenticate
    @PostMapping
    public ResponseEntity<String> addItem(@RequestBody Item item) {
        ResponseEntity responseEntity = null;

        if (itemService.isItemExists(item.getItemId())) {
            LOGGER.warn("Item " + item.getItemId() + "already exists");
            responseEntity = new ResponseEntity<String>("Cannot save Item " + item.getItemId() + " already exists", HttpStatus.CONFLICT);

        } else {
            itemService.addItem(item);
            responseEntity = new ResponseEntity<String>("Successfully Saved Item " + item.getItemId(), HttpStatus.OK);
            LOGGER.info("Item " + item.getItemId() + " saved successfully");
        }
        return responseEntity;
    }

}
