package com.example.gymsystem.entity;

import jakarta.persistence.*;

import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Data
public abstract class Membership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private BigDecimal price;

    @OneToMany(mappedBy = "membership")
    private List<Member> members;

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}