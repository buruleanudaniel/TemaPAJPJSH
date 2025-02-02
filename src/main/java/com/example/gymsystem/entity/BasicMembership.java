package com.example.gymsystem.entity;

import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
public class BasicMembership extends Membership {
    private BigDecimal discount;

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
}