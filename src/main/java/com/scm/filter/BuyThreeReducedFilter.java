package com.scm.filter;

import com.scm.model.Item;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BuyThreeReducedFilter extends AbstractFilter{

    @Override
    public double filterPrice(List<Item> items)  {
        if (items.size() == 0) {
            return 0;
        }
        AtomicInteger numberOfGroup = new AtomicInteger(items.size() / 3);
        AtomicInteger numberRemain = new AtomicInteger(items.size() % 3);
        return numberOfGroup.get() * 130.00 + numberRemain.get() * items.get(0).getPrice();
    }
}
