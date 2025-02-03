package com.example.gymsystem.entity;

import jakarta.persistence.Entity;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Entity
public class BasicMembership extends Membership {
    private BigDecimal discount;

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
}