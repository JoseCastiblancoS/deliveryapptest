package com.workshop.deliveryapptest.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryOrder {
    
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "customer_name")
    private String customerName;
    
    @Column(name = "customer_address")
    private String customerAddress;
    
    @Column(name = "customer_email")
    private String customerEmail;
    
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    
    @ElementCollection
    private List<String> items;
    
    @Column(name = "assigned_to")
    private String assignedTo;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    public enum OrderStatus {
        PROCESSING,
        IN_TRANSIT,
        DELIVERED
    }

}
