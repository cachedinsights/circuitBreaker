package com.codedTribe.CircuitBreaker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Item
{
    private long itemId;
    private String name;
    private long price;
    private int quantity;
}
