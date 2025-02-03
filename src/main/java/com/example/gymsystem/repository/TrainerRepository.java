package com.example.gymsystem.repository;

import com.example.gymsystem.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {

    List<Trainer> findBySpecialization(String specialization);
}