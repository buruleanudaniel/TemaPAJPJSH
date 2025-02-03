package com.example.gymsystem.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Getter
@Entity
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @ManyToOne
    private Membership membership;

    @ManyToOne
    private Trainer trainer;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }
}