package com.uc.controller;

import com.uc.Exception.ItemNotFoundException;
import com.uc.bean.Item;
import com.uc.service.ItemDaoService;
import com.uc.service.jpa.ItemRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/* To access swagger, URL is http://localhost:8080/swagger-ui/index.html */

@RestController
@RequestMapping("/V1")
public class ItemJPAController {

    //    @Autowired
    private ItemDaoService service;
    private ItemRepository repository;

    public ItemJPAController(ItemDaoService service, ItemRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping("/jpa/getAllItems")
    public List<Item> getAllItems() {
        return repository.findAll();
    }

    @GetMapping("/jpa/getItem/{id}")
    public Item getItem(@PathVariable int id) {
        Optional<Item> item = repository.findById(id);
        if (item.isEmpty()) {
            throw new ItemNotFoundException(String.format("Item with id : %s not found", id));
        }
        return item.get();
    }

    @PostMapping("/jpa/createItem")
    public ResponseEntity<Item> createItem(@Valid @RequestBody Item item) {
        Item createdItem = repository.save(item);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdItem.getId())
                .toUri();
        return ResponseEntity.created(location).build();

    }

    @DeleteMapping("/jpa/deleteItem/{id}")
    public void deleteItem(@PathVariable int id) {
        repository.deleteById(id);
    }
}
