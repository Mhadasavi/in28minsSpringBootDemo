package com.uc.controller;

import com.uc.Exception.ItemNotFoundException;
import com.uc.bean.Item;
import com.uc.service.ItemDaoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/* To access swagger, URL is http://localhost:8080/swagger-ui/index.html */

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
        Item item = service.getItem(id);
        if (item == null) {
            throw new ItemNotFoundException(String.format("Item with id : %s not found", id));
        }
        return item;
    }

    @PostMapping("/createItem")
    public ResponseEntity<Item> createItem(@Valid @RequestBody Item item) {
        Item createdItem = service.createItem(item);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdItem.getId())
                .toUri();
        return ResponseEntity.created(location).build();

    }

    @DeleteMapping("/deleteItem/{id}")
    public void deleteItem(@PathVariable int id) {
        service.deleteItemById(id);
    }
}
