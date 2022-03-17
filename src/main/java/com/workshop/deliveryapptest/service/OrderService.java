package com.workshop.deliveryapptest.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.workshop.deliveryapptest.dto.OrderDto;
import com.workshop.deliveryapptest.model.DeliveryOrder;
import com.workshop.deliveryapptest.model.DeliveryOrder.OrderStatus;
import com.workshop.deliveryapptest.repository.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderService {
    
    @Value("${deliveryapp.partners}")
    private List<String> deliveryPartners;
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private EmailService emailService;
    
    public List<DeliveryOrder> getOrders() {
        return orderRepository.findAll();
    }
    
    @Async
    public void processOrder(OrderDto orderDto) {
        
        int index = new Random().nextInt(deliveryPartners.size());
        String assignedTo = deliveryPartners.get(index);
        
        log.info("Order assigned to partner {}", assignedTo);
        
        DeliveryOrder order = DeliveryOrder.builder()
                .assignedTo(assignedTo)
                .createdAt(LocalDateTime.now())
                .customerAddress(orderDto.getCustomerAddress())
                .customerEmail(orderDto.getCustomerEmail())
                .customerName(orderDto.getCustomerName())
                .items(orderDto.getItems())
                .status(OrderStatus.PROCESSING)
                .build();
        orderRepository.save(order);
        
        emailService.sendEmail(assignedTo, "Order assigned with id " + order.getId(), formatMessageBody(order));
    }

    private String formatMessageBody(DeliveryOrder order) {
        return String.format("Customer name: %s \n"
                + "Customer address: %s \n"
                + "Items: \n %s", order.getCustomerName(), order.getCustomerAddress(), String.join("\n", order.getItems()));
    }

}
