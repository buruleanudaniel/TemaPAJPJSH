package com.example.gymsystem.entity;

import jakarta.persistence.Entity;
import lombok.Getter;

@Getter
@Entity
public class PremiumMembership extends Membership {
    private Boolean personalTrainingIncluded;

    public void setPersonalTrainingIncluded(Boolean personalTrainingIncluded) {
        this.personalTrainingIncluded = personalTrainingIncluded;
    }
}