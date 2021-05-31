package com.scm.service;

import com.scm.model.Inventory;
import com.scm.model.Item;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class Cart implements ICart {
    private List<Item> items = Collections.synchronizedList(new ArrayList());
    private Inventory inventory;

    public Cart(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public List<Item> getItems() {
        return items;
    }

    @Override
    public void empty() {
        this.items.clear();
    }

    @Override
    public void add(String itemName) {
        Item inventoryItem = inventory.getListingItems().get(itemName);
        items.add(new Item(inventoryItem));

    }

    @Override
    public void add(List<String> itemNames) {
        itemNames.forEach((itemName) -> {
                add(itemName);
        });
    }

    @Override
    public double calculateFinalPrice() {
        double finalPrice = 0;
        ConcurrentMap<String, List<Item>> groupItems = items.stream().collect(Collectors.groupingByConcurrent(Item::getName));
        for (ConcurrentMap.Entry<String, List<Item>> listItemEntry : groupItems.entrySet()) {
            finalPrice += inventory.getFilter().get(listItemEntry.getKey()).filterPrice(listItemEntry.getValue());
        }
        return finalPrice;
    }

    @Override
    public double calculateNoFilterPrice() {
        double defaultPrice = 0;
        ConcurrentMap<String, List<Item>> groupItems = items.stream().collect(Collectors.groupingByConcurrent(Item::getName));
        for (ConcurrentMap.Entry<String, List<Item>> listItemEntry : groupItems.entrySet()) {
            defaultPrice += inventory.defaultPriceFilter().filterPrice(listItemEntry.getValue());
        }
        return defaultPrice;
    }
}
