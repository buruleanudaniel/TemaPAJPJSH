package com.example.gymsystem;

import com.example.gymsystem.entity.Trainer;
import com.example.gymsystem.repository.TrainerRepository;
import com.example.gymsystem.service.TrainerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Import(DataInitializer.class)
class TrainerServiceTest {

    @Autowired
    private TrainerService trainerService;

    @Autowired
    private TrainerRepository trainerRepository;

    @Test
    void testGetAllTrainers() {
        List<Trainer> result = trainerService.getAllTrainers();

        assertEquals(2, result.size());
    }

    @Test
    void testGetTrainersBySpecialization() {
        List<Trainer> yogaTrainers = trainerService.getTrainersBySpecialization("Yoga");

        assertEquals(1, yogaTrainers.size());
        assertEquals("Jane Smith", yogaTrainers.get(0).getName());

        List<Trainer> strengthTrainers = trainerService.getTrainersBySpecialization("Strength Training");

        assertEquals(1, strengthTrainers.size());
        assertEquals("John Doe", strengthTrainers.get(0).getName());
    }

    @Test
    void testAddTrainer() {
        Trainer trainer = new Trainer("Mike Johnson", "Cardio");

        Trainer savedTrainer = trainerService.addTrainer(trainer);

        assertEquals("Mike Johnson", savedTrainer.getName());
        assertEquals("Cardio", savedTrainer.getSpecialization());
    }
}