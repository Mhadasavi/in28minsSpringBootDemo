package com.uc.service;

import com.uc.bean.Item;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class ItemDaoService {

    private static List<Item> itemList = new ArrayList<>();

    private static int autoIncrementId = 0;

    static {
        itemList.add(new Item(++autoIncrementId, "item A", 10, 10, "piece", 10, LocalDate.now()));
        itemList.add(new Item(++autoIncrementId, "item B", 20, 20, "piece", 20, LocalDate.now()));
        itemList.add(new Item(++autoIncrementId, "item C", 30, 30, "piece", 30, LocalDate.now()));
    }

    public List<Item> getAllItems() {
        return itemList;
    }

    public Item getItem(int id) {
//        Predicate<? super Item> predicate = item -> item.getId() == id;
        return itemList.stream().filter(item -> item.getId() == id).findFirst().orElse(null);
    }

    public Item createItem(Item item) {
        item.setId(++autoIncrementId);
        itemList.add(item);
        return item;
    }
}
