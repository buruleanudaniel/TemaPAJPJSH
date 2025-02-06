package com.example.gymsystem;

import com.example.gymsystem.entity.Member;
import com.example.gymsystem.entity.Membership;
import com.example.gymsystem.entity.Trainer;
import com.example.gymsystem.entity.WorkoutPlan;
import com.example.gymsystem.repository.MemberRepository;
import com.example.gymsystem.repository.MembershipRepository;
import com.example.gymsystem.repository.TrainerRepository;
import com.example.gymsystem.repository.WorkoutPlanRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class DataInitializer {
    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Bean
    CommandLineRunner initDatabase(
            MemberRepository memberRepository,
            MembershipRepository membershipRepository,
            TrainerRepository trainerRepository,
            WorkoutPlanRepository workoutPlanRepository
    ) {
        return args -> {
            // Create Memberships
            Membership basic = membershipRepository.save(new Membership("Basic", 50.0, java.time.Duration.ofDays(30)));
            Membership premium = membershipRepository.save(new Membership("Premium", 100.0, java.time.Duration.ofDays(90)));

            // Create Trainers
            Trainer trainer1 = trainerRepository.save(new Trainer("John Doe", "Strength Training"));
            Trainer trainer2 = trainerRepository.save(new Trainer("Jane Smith", "Yoga"));

            // Create Members
            Member alice = memberRepository.save(new Member("Alice Johnson", "alice@example.com", basic));
            Member bob = memberRepository.save(new Member("Bob Brown", "bob@example.com", premium));

            // Create Workout Plans
            WorkoutPlan upperBodyWorkout = new WorkoutPlan("Upper Body Workout", LocalDate.now(), alice, trainer1);
            WorkoutPlan coreStrengthening = new WorkoutPlan("Core Strengthening", LocalDate.now(), bob, trainer2);

            workoutPlanRepository.save(upperBodyWorkout);
            workoutPlanRepository.save(coreStrengthening);

            logger.info("Initialized Workout Plans: {}", workoutPlanRepository.findAll().size());
        };
    }
}