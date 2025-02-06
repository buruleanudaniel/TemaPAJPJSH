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
        member = new Member("Alice", "alice@example.com", null);
        member = memberRepository.save(member);

        trainer = new Trainer("John Doe", "Strength Training");
        trainer = trainerRepository.save(trainer);

        workoutPlan = new WorkoutPlan("Upper Body Workout", LocalDate.now(), member, trainer);
        workoutPlanRepository.save(workoutPlan);

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
        List<WorkoutPlan> plans = workoutPlanRepository.findByMember_Id(member.getId());

        assertNotNull(plans);
        assertFalse(plans.isEmpty(), "Expected at least one workout plan");
        assertEquals(1, plans.size(), "Expected exactly one workout plan");

        WorkoutPlan retrievedPlan = plans.get(0);
        assertNotNull(retrievedPlan);
        assertEquals(workoutPlan.getDescription(), retrievedPlan.getDescription());
        assertEquals(workoutPlan.getStartDate(), retrievedPlan.getStartDate());
        assertEquals(workoutPlan.getMember().getId(), retrievedPlan.getMember().getId());
        assertEquals(workoutPlan.getTrainer().getId(), retrievedPlan.getTrainer().getId());
    }

    @Test
    void testFindByTrainerId() {
        List<WorkoutPlan> plans = workoutPlanRepository.findByTrainer_Id(trainer.getId());

        assertNotNull(plans);
        assertFalse(plans.isEmpty(), "Expected at least one workout plan");
        assertEquals(1, plans.size(), "Expected exactly one workout plan");

        WorkoutPlan retrievedPlan = plans.get(0);
        assertNotNull(retrievedPlan);
        assertEquals(workoutPlan.getDescription(), retrievedPlan.getDescription());
        assertEquals(workoutPlan.getStartDate(), retrievedPlan.getStartDate());
        assertEquals(workoutPlan.getMember().getId(), retrievedPlan.getMember().getId());
        assertEquals(workoutPlan.getTrainer().getId(), retrievedPlan.getTrainer().getId());
    }
}