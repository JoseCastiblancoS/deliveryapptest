package com.workshop.deliveryapptest.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.workshop.deliveryapptest.repository.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CongratsService {
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private EmailService emailService;
    
    @Scheduled(cron = " * 0/5 * * * ?")
    public void congratsPartner() {
        Optional<String> winnerPartner = orderRepository.getPartnerFromTheHour(LocalDateTime.now().minusHours(1L));
        
        if(winnerPartner.isPresent()) {
            log.info("The winner partner is {}", winnerPartner.get());
            emailService.sendEmail(winnerPartner.get(), "Congrats!", "Congratulations! You are the partner of the hour :)");
        }
    }

}
