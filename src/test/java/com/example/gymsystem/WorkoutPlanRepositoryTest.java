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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

    @BeforeEach
    void setUp() {
        member = memberRepository.save(new Member("Alice Johnson", "alice@example.com", null));
        trainer = trainerRepository.save(new Trainer("John Doe", "Strength Training"));

        workoutPlanRepository.save(new WorkoutPlan("Upper Body Workout", LocalDate.now(), member, trainer));
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
        assertEquals(1, plans.size());
        assertEquals("Upper Body Workout", plans.get(0).getDescription());
    }

    @Test
    void testFindByTrainerId() {
        List<WorkoutPlan> plans = workoutPlanRepository.findByTrainer_Id(trainer.getId());
        assertNotNull(plans);
        assertEquals(1, plans.size());
        assertEquals("Upper Body Workout", plans.get(0).getDescription());
    }
}