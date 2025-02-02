package com.example.gymsystem.entity;

import jakarta.persistence.*;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}