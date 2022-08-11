package com.codedTribe.CircuitBreaker.utils;

import com.codedTribe.CircuitBreaker.exception.OrderServiceException;
import com.codedTribe.CircuitBreaker.model.Item;
import com.codedTribe.CircuitBreaker.model.Order;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class SampleData
{

    private static long COUNT = -1;

    public List<Order> getDummyOrders(Boolean failure)
    {
        Item item1 = new Item(1,"ITEM1",999,4);
        Item item2 = new Item(2,"ITEM2",199,10);
        Item item3 = new Item(3,"ITEM3",299,5);
        Item item4 = new Item(4,"ITEM4",499,20);
        Item item5 = new Item(5,"ITEM5",249,10);

        List<Item> items = Arrays.asList(item1, item2, item3, item4, item5);
        long total = getTotalPrice(items);
        Order order = new Order(1,items, total);


        if(!failure)
            return Arrays.asList(order);
        else
            throw new OrderServiceException("Error Occurred while getting the orders.");
    }

    public List<Order> getDummyOrders(Long sleep) throws InterruptedException {
        Item item1 = new Item(1,"ITEM1",999,4);
        Item item2 = new Item(2,"ITEM2",199,10);
        Item item3 = new Item(3,"ITEM3",299,5);
        Item item4 = new Item(4,"ITEM4",499,20);
        Item item5 = new Item(5,"ITEM5",249,10);

        List<Item> items = Arrays.asList(item1, item2, item3, item4, item5);
        long total = getTotalPrice(items);
        Order order = new Order(1,items, total);

        Thread.sleep(sleep);
        return Arrays.asList(order);
    }

    private long getTotalPrice(List<Item> items)
    {
        long total = 0;
        for (Item item: items)
        {
           total = total + (item.getQuantity() * item.getPrice());
        }
        return total;
    }
}
