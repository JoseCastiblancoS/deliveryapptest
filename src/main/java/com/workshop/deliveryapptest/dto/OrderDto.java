package com.workshop.deliveryapptest.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class OrderDto implements Serializable {

    private static final long serialVersionUID = -5048227664224214186L;
    
    private String customerName;
    private String customerEmail;
    private String customerAddress;
    private List<String> items;

}
