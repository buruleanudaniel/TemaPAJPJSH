package com.example.gymsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private LocalDate registrationDate;

    @ManyToOne
    private Membership membership;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<WorkoutPlan> workoutPlans;

    public Member() {}

    public Member(String name, String email, Membership membership) {
        this.name = name;
        this.email = email;
        this.membership = membership;
        this.registrationDate = LocalDate.now();
    }
}