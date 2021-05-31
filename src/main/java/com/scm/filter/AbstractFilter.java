package com.scm.filter;

import com.scm.model.Item;

import java.util.List;

public abstract class AbstractFilter {
    public abstract double filterPrice(List<Item> items);
}
