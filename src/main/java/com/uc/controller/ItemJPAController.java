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
@RequestMapping("/V1")
public class ItemJPAController {

    //    @Autowired
    private ItemDaoService service;

    public ItemJPAController(ItemDaoService service) {
        this.service = service;
    }

    @GetMapping("/jpa/getAllItems")
    public List<Item> getAllItems() {
        return service.getAllItems();
    }

    @GetMapping("/jpa/getItem/{id}")
    public Item getItem(@PathVariable int id) {
        Item item = service.getItem(id);
        if (item == null) {
            throw new ItemNotFoundException(String.format("Item with id : %s not found", id));
        }
        return item;
    }

    @PostMapping("/jpa/createItem")
    public ResponseEntity<Item> createItem(@Valid @RequestBody Item item) {
        Item createdItem = service.createItem(item);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdItem.getId())
                .toUri();
        return ResponseEntity.created(location).build();

    }

    @DeleteMapping("/jpa/deleteItem/{id}")
    public void deleteItem(@PathVariable int id) {
        service.deleteItemById(id);
    }
}
