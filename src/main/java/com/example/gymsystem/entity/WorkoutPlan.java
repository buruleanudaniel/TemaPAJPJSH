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
    @JoinColumn(name = "member_id") // coloana de foreign key
    private Member member;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    public WorkoutPlan() {}

    public WorkoutPlan(String description, LocalDate startDate, Member member, Trainer trainer) {
        this.description = description;
        this.startDate = startDate;
        this.member = member;
        this.trainer = trainer;
    }

    public WorkoutPlan(String description, Member member, Trainer trainer) {
        this.description = description;
        this.startDate = LocalDate.now();
        this.member = member;
        this.trainer = trainer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }
}