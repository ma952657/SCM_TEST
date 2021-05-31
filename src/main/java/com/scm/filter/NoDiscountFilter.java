package com.scm.filter;

import com.scm.model.Item;
import java.util.List;

public class NoDiscountFilter extends AbstractFilter {
    @Override
    public double filterPrice(List<Item> items)  {
        if (items.size() == 0) {
            return 0;
        }
        return items.size() * items.stream().findFirst().get().getPrice();
    }
}
