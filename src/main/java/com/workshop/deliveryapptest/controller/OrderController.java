package com.workshop.deliveryapptest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workshop.deliveryapptest.dto.OrderDto;
import com.workshop.deliveryapptest.model.DeliveryOrder;
import com.workshop.deliveryapptest.service.OrderService;

@RestController
@RequestMapping(value = "/orders", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {
    
    @Autowired
    private OrderService orderService;
    
    @GetMapping(value = "/list")
    public ResponseEntity<List<DeliveryOrder>> getLists() {
        return new ResponseEntity<>(orderService.getOrders(), HttpStatus.OK);
    }
    
    @PostMapping(value = "/process")
    public ResponseEntity<String> processOrder(@RequestBody OrderDto orderDto) {
        orderService.processOrder(orderDto);
        return new ResponseEntity<>("Your order is being processed!", HttpStatus.OK);
    }

}
