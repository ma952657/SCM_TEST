package com.domain.service;

import com.scm.filter.PromotionType;
import com.scm.model.Inventory;
import com.scm.model.Item;
import com.scm.service.Cart;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.*;

public class CartTest {
    private Inventory inventory;
    private List<Item> items = new CopyOnWriteArrayList<Item>();
    private List<PromotionType> itemPromotions = new CopyOnWriteArrayList<PromotionType>();

    @Before
    public void setUp() {
        items.add(new Item("A", 50.00));
        items.add(new Item("B", 30));
        items.add(new Item("C", 20.00));
        items.add(new Item("D", 15.00));
        itemPromotions.add(PromotionType.BUY_THREE_OR_MORE_REDUCED_PRICES);
        itemPromotions.add(PromotionType.BUY_TWO_OR_MORE_REDUCED_PRICES);
        itemPromotions.add(PromotionType.NO_DISCOUNT_FILTER);
        itemPromotions.add(PromotionType.NO_DISCOUNT_FILTER);
        inventory = new Inventory(items, itemPromotions);
    }


    @Test
    public void testCreateBasket()  {
        List<String> order = new CopyOnWriteArrayList<String>(Arrays.asList("A", "B", "B", "C", "C", "C", "A", "A", "B", "A","D"));
        Cart cart = new Cart(inventory);
        cart.add(order);
        assertNotNull(cart);
        assertTrue(cart.getItems().size() > 0);

    }

    @Test
    public void testCalculateFinalPriceScenario1()  {
        List<String> order = new CopyOnWriteArrayList<String>(Arrays.asList("A", "B","C"));
        Cart cart = new Cart(inventory);
        cart.add(order);
        assertEquals(cart.calculateFinalPrice(), 100.00,0.001);

    }

    @Test
    public void testCalculateFinalPriceScenario2()  {
        List<String> order = new CopyOnWriteArrayList<String>(Arrays.asList("A", "B","C","A", "B","A", "B","A", "B","A", "B"));
        Cart cart = new Cart(inventory);
        cart.add(order);
        assertEquals(cart.calculateFinalPrice(), 370.00,0.001);

    }
}
