package com.example.gymsystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class WorkoutPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private LocalDate startDate;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Trainer trainer;

    public WorkoutPlan() {}

    public WorkoutPlan(String description, Member member, Trainer trainer) {
        this.description = description;
        this.member = member;
        this.trainer = trainer;
        this.startDate = LocalDate.now();
    }

    public WorkoutPlan(String upperBodyWorkout, LocalDate now, Member member, Trainer trainer) {
    }
}