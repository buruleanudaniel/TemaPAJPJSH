package com.example.gymsystem;

import com.example.gymsystem.entity.Member;
import com.example.gymsystem.entity.Trainer;
import com.example.gymsystem.entity.WorkoutPlan;
import com.example.gymsystem.repository.MemberRepository;
import com.example.gymsystem.repository.TrainerRepository;
import com.example.gymsystem.repository.WorkoutPlanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class WorkoutPlanRepositoryTest {

    @Autowired
    private WorkoutPlanRepository workoutPlanRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    private Member member;
    private Trainer trainer;
    private WorkoutPlan workoutPlan;

    @BeforeEach
    void setUp() {
        // Save a Member
        member = new Member("Alice", "alice@example.com", null);
        member = memberRepository.save(member);

        // Save a Trainer
        trainer = new Trainer("John Doe", "Strength Training");
        trainer = trainerRepository.save(trainer);

        // Save a WorkoutPlan associated with the Member and Trainer
        workoutPlan = new WorkoutPlan("Upper Body Workout", LocalDate.now(), member, trainer);
        workoutPlanRepository.save(workoutPlan); // Ensure the WorkoutPlan is saved

        // Verify that the WorkoutPlan was saved
        assertNotNull(workoutPlan.getId());
        assertEquals(member.getId(), workoutPlan.getMember().getId());
        assertEquals(trainer.getId(), workoutPlan.getTrainer().getId());
    }

    @Test
    void testFindAllWorkoutPlans() {
        List<WorkoutPlan> plans = workoutPlanRepository.findAll();
        assertNotNull(plans);
        assertEquals(1, plans.size());
    }

    @Test
    void testFindByMemberId() {
        // Query for WorkoutPlans associated with the Member
        List<WorkoutPlan> plans = workoutPlanRepository.findByMember_Id(member.getId());

        // Verify the result
        assertNotNull(plans);
        assertFalse(plans.isEmpty(), "Expected at least one workout plan");
        assertEquals(1, plans.size(), "Expected exactly one workout plan");

        // Verify the details of the retrieved plan
        WorkoutPlan retrievedPlan = plans.get(0);
        assertNotNull(retrievedPlan);
        assertEquals(workoutPlan.getDescription(), retrievedPlan.getDescription());
        assertEquals(workoutPlan.getStartDate(), retrievedPlan.getStartDate());
        assertEquals(workoutPlan.getMember().getId(), retrievedPlan.getMember().getId());
        assertEquals(workoutPlan.getTrainer().getId(), retrievedPlan.getTrainer().getId());
    }

    @Test
    void testFindByTrainerId() {
        // Query for WorkoutPlans associated with the Trainer
        List<WorkoutPlan> plans = workoutPlanRepository.findByTrainer_Id(trainer.getId());

        // Verify the result
        assertNotNull(plans);
        assertFalse(plans.isEmpty(), "Expected at least one workout plan");
        assertEquals(1, plans.size(), "Expected exactly one workout plan");

        // Verify the details of the retrieved plan
        WorkoutPlan retrievedPlan = plans.get(0);
        assertNotNull(retrievedPlan);
        assertEquals(workoutPlan.getDescription(), retrievedPlan.getDescription());
        assertEquals(workoutPlan.getStartDate(), retrievedPlan.getStartDate());
        assertEquals(workoutPlan.getMember().getId(), retrievedPlan.getMember().getId());
        assertEquals(workoutPlan.getTrainer().getId(), retrievedPlan.getTrainer().getId());
    }
}