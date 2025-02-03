package com.example.gymsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;

@Entity
@Getter
@Setter
public class Membership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String planName; // Ensure this field exists
    private double price;
    private Duration duration;

    public Membership() {}

    public Membership(String planName, double price, Duration duration) {
        this.planName = planName;
        this.price = price;
        this.duration = duration;
    }
}