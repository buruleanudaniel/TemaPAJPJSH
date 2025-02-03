package com.example.gymsystem;

import com.example.gymsystem.entity.Trainer;
import com.example.gymsystem.repository.TrainerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class TrainerRepositoryTest {

    @Autowired
    private TrainerRepository trainerRepository;

    @BeforeEach
    void setUp() {
        trainerRepository.save(new Trainer("John Doe", "Strength Training"));
        trainerRepository.save(new Trainer("Jane Smith", "Yoga"));
    }

    @Test
    void testFindAllTrainers() {
        List<Trainer> trainers = trainerRepository.findAll();
        assertNotNull(trainers);
        assertEquals(2, trainers.size());
    }

    @Test
    void testFindBySpecialization() {
        List<Trainer> trainers = trainerRepository.findBySpecialization("Strength Training");
        assertNotNull(trainers);
        assertEquals(1, trainers.size());
        assertEquals("John Doe", trainers.get(0).getName());
    }
}