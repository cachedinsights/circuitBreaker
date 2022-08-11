package com.codedTribe.CircuitBreaker.service;

import com.codedTribe.CircuitBreaker.model.Order;
import com.codedTribe.CircuitBreaker.utils.SampleData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService
{
    @Autowired
    private SampleData sampleData;

    public List<Order> getOrders(Boolean failure)
    {
            return sampleData.getDummyOrders(failure);
    }
    public List<Order> getOrders(Long sleep) throws InterruptedException {
        return sampleData.getDummyOrders(sleep);
    }
}
