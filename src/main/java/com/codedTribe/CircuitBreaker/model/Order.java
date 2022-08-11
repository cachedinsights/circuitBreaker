package com.codedTribe.CircuitBreaker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@Data
@ToString
public class Order
{
    private long orderId;
    private List<Item> items;
    private long total;
}
