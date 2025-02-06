package com.example.gymsystem;

import com.example.gymsystem.entity.Member;
import com.example.gymsystem.entity.Trainer;
import com.example.gymsystem.entity.WorkoutPlan;
import com.example.gymsystem.repository.MemberRepository;
import com.example.gymsystem.repository.TrainerRepository;
import com.example.gymsystem.repository.WorkoutPlanRepository;
import com.example.gymsystem.service.WorkoutPlanService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Import(DataInitializer.class)
class WorkoutPlanServiceTest {

    @Autowired
    private WorkoutPlanService workoutPlanService;

    @Autowired
    private WorkoutPlanRepository workoutPlanRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    @Test
    void testGetAllWorkoutPlans() {
        List<WorkoutPlan> result = workoutPlanService.getAllWorkoutPlans();

        assertEquals(3, result.size());
    }

    @Test
    void testGetWorkoutPlansByMemberId() {
        Member alice = memberRepository.findByEmail("alice@example.com").orElseThrow();

        List<WorkoutPlan> aliceWorkoutPlans = workoutPlanService.getWorkoutPlansByMemberId(alice.getId());

        assertEquals(1, aliceWorkoutPlans.size());
        assertEquals("Upper Body Workout", aliceWorkoutPlans.get(0).getDescription());
    }

    @Test
    void testGetWorkoutPlansByTrainerId() {
        Trainer johnDoe = trainerRepository.findByName("John Doe").orElseThrow();

        List<WorkoutPlan> johnDoeWorkoutPlans = workoutPlanService.getWorkoutPlansByTrainerId(johnDoe.getId());

        assertEquals(1, johnDoeWorkoutPlans.size());
        assertEquals("Upper Body Workout", johnDoeWorkoutPlans.get(0).getDescription());
    }

    @Test
    void testAddWorkoutPlan() {
        Member bob = memberRepository.findByEmail("bob@example.com").orElseThrow();
        Trainer janeSmith = trainerRepository.findByName("Jane Smith").orElseThrow();

        WorkoutPlan newWorkoutPlan = new WorkoutPlan("Leg Day", LocalDate.now(), bob, janeSmith);

        WorkoutPlan savedWorkoutPlan = workoutPlanService.addWorkoutPlan(newWorkoutPlan);

        assertEquals("Leg Day", savedWorkoutPlan.getDescription());
        assertEquals(bob.getId(), savedWorkoutPlan.getMember().getId());
        assertEquals(janeSmith.getId(), savedWorkoutPlan.getTrainer().getId());
    }
}