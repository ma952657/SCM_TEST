package com.scm.service;

import com.scm.model.Item;

import java.util.List;

public interface ICart {

    public List<Item> getItems();

    public void empty();

    public void add(String itemName);

    public void add(List<String> itemNames);

    public double calculateFinalPrice();

    public double calculateNoFilterPrice();
}
