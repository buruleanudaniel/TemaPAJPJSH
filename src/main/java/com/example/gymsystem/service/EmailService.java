package com.example.gymsystem.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class EmailService {
    @Async
    public void sendEmail(String to, String subject, String body) {
        try {
            TimeUnit.SECONDS.sleep(2); // Simulate delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Email sent to: " + to);
    }
}