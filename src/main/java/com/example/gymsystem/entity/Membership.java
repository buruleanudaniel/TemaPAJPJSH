package com.example.gymsystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Duration;

@Entity
@Data
public class Membership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String planName;
    private double price;
    private Duration duration; // Duration of the membership in days

    public Membership() {}

    public Membership(String planName, double price, Duration duration) {
        this.planName = planName;
        this.price = price;
        this.duration = duration;
    }
}