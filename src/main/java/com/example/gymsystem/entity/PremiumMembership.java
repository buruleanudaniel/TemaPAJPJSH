package com.example.gymsystem.entity;

import jakarta.persistence.Entity;

@Entity
public class PremiumMembership extends Membership {
    private Boolean personalTrainingIncluded;

    public Boolean getPersonalTrainingIncluded() {
        return personalTrainingIncluded;
    }

    public void setPersonalTrainingIncluded(Boolean personalTrainingIncluded) {
        this.personalTrainingIncluded = personalTrainingIncluded;
    }
}