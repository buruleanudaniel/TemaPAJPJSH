package com.example.gymsystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String specialization;

    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL)
    private List<WorkoutPlan> workoutPlans;

    public Trainer() {}

    public Trainer(String name, String specialization) {
        this.name = name;
        this.specialization = specialization;
    }
}