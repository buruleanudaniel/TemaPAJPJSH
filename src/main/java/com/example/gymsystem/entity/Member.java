package com.example.gymsystem.entity;

import com.example.gymsystem.entity.Membership;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @Column(unique = true)
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