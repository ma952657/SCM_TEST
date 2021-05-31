package com.scm.model;

import com.scm.filter.*;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.IntStream;

public class Inventory {

    private ConcurrentMap<String, Item> listingItems = new ConcurrentHashMap<String, Item>();
    private ConcurrentMap<String, PromotionType> promotions = new ConcurrentHashMap<String, PromotionType>();
    private ConcurrentMap<String, AbstractFilter> filter = new ConcurrentHashMap<String, AbstractFilter>();
    private NoDiscountFilter noDiscountFilter;
    private BuyTwoReducedFilter buyTwoReducedFilter;
    private BuyThreeReducedFilter buyThreeReducedFilter;

    public ConcurrentMap<String, Item> getListingItems() {
        return listingItems;
    }

    public ConcurrentMap<String, PromotionType> getPromotions() {
        return promotions;
    }

    public ConcurrentMap<String, AbstractFilter> getFilter() {
        return filter;
    }

    public AbstractFilter defaultPriceFilter() {
        return new NoDiscountFilter();
    }

    public Inventory(List<Item> items, List<PromotionType> itemPromotions) {
        noDiscountFilter = new NoDiscountFilter();
        buyTwoReducedFilter = new BuyTwoReducedFilter();
        buyThreeReducedFilter = new BuyThreeReducedFilter();
        IntStream.range(0, items.size()).forEach(i -> {
            Item item = items.get(i);
            listingItems.put(item.getName(), item);
            promotions.put(item.getName(), itemPromotions.get(i));
            if (itemPromotions.get(i).equals(PromotionType.BUY_TWO_OR_MORE_REDUCED_PRICES)) {
                filter.put(item.getName(), buyTwoReducedFilter);
            } else if (itemPromotions.get(i).equals(PromotionType.BUY_THREE_OR_MORE_REDUCED_PRICES)) {
                filter.put(item.getName(), buyThreeReducedFilter);
            } else {
                filter.put(item.getName(), noDiscountFilter);
            }
        });
    }
}
