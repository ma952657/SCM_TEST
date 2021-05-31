package com.scm.filter;

import com.scm.model.Item;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BuyTwoReducedFilter extends AbstractFilter {
    @Override
    public double filterPrice(List<Item> items)  {
        if (items.size() == 0) {
            return 0;
        }
        AtomicInteger numberOfGroup = new AtomicInteger(items.size() / 2);
        AtomicInteger numberRemain = new AtomicInteger(items.size() % 2);
        return numberOfGroup.get() * 45.00 + numberRemain.get() * items.get(0).getPrice();
    }
}
