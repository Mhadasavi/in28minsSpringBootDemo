package com.uc.controller;

import com.uc.bean.Item;
import com.uc.service.ItemDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    //    @Autowired
    private ItemDaoService service;

    public ItemController(ItemDaoService service) {
        this.service = service;
    }

    @GetMapping("/getAllItems")
    public List<Item> getAllItems() {
        return service.getAllItems();
    }

    @GetMapping("/getItem/{id}")
    public Item getItem(@PathVariable int id) {
        return service.getItem(id);
    }

    @PostMapping("/createItem")
    public void createItem(@RequestBody Item item) {
        service.createItem(item);
    }
}
