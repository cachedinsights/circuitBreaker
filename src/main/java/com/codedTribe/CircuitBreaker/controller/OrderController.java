package com.codedTribe.CircuitBreaker.controller;

import com.codedTribe.CircuitBreaker.model.Order;
import com.codedTribe.CircuitBreaker.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController
{
    @Autowired
    private OrderService orderService;

    private static Logger logger = LoggerFactory.getLogger(OrderController.class);

    @GetMapping("/{failure}")
    @CircuitBreaker(name = "serviceA", fallbackMethod = "fallback")
    public ResponseEntity getOrders(@PathVariable("failure") Boolean failure)
    {
        logger.info("Inside get orders api.");
        List<Order> orders = orderService.getOrders(failure);
        return new ResponseEntity(orders, HttpStatus.OK);
    }

    @GetMapping("/slow/{sleep}")
    @CircuitBreaker(name = "serviceB", fallbackMethod = "fallback")
    public ResponseEntity getOrdersSlow(@PathVariable("sleep") Long sleep) throws InterruptedException {
        logger.info("Inside get orders slow api.");
        Thread.sleep(2000);
        List<Order> orders = orderService.getOrders(sleep);
        return new ResponseEntity(orders, HttpStatus.OK);
    }

    private ResponseEntity fallback(RuntimeException e)
    {
        logger.info("Inside fallback api.");
        String message = e.getMessage();
        logger.info("fallback : {}",e.getStackTrace());
        return new ResponseEntity(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
