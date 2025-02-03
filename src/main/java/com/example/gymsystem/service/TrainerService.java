package com.example.gymsystem.service;

import com.example.gymsystem.entity.Trainer;
import com.example.gymsystem.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerService {

    private final TrainerRepository trainerRepository;

    @Autowired
    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    public List<Trainer> getAllTrainers() {
        return trainerRepository.findAll();
    }

    public List<Trainer> getTrainersBySpecialization(String specialization) {
        return trainerRepository.findBySpecialization(specialization);
    }

    public Trainer addTrainer(Trainer trainer) {
        return trainerRepository.save(trainer);
    }
}