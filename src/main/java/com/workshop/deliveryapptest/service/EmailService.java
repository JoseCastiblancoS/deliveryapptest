package com.workshop.deliveryapptest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailService {
    
    @Autowired
    private JavaMailSender mailSender;
    
    @Value("${spring.mail.username}")
    private String from;
    
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        
        mailSender.send(message);
        log.info("Email sent from {} to {} with subject {}", from, to, subject);
    }

}
