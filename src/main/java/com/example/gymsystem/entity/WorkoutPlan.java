package com.example.gymsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class WorkoutPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private LocalDate startDate;

    @ManyToOne
    @JoinColumn(name = "member_id") // Define the foreign key column
    private Member member;

    @ManyToOne
    @JoinColumn(name = "trainer_id") // Define the foreign key column
    private Trainer trainer;

    public WorkoutPlan() {}

    public WorkoutPlan(String description, LocalDate startDate, Member member, Trainer trainer) {
        this.description = description;
        this.startDate = startDate;
        this.member = member;
        this.trainer = trainer;
    }

    public WorkoutPlan(String coreStrengthening, Member bob, Trainer trainer2) {
    }
}