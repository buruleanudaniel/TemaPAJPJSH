package com.example.gymsystem.service;

import com.example.gymsystem.entity.WorkoutPlan;
import com.example.gymsystem.repository.WorkoutPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutPlanService {

    private final WorkoutPlanRepository workoutPlanRepository;

    @Autowired
    public WorkoutPlanService(WorkoutPlanRepository workoutPlanRepository) {
        this.workoutPlanRepository = workoutPlanRepository;
    }

    public List<WorkoutPlan> getAllWorkoutPlans() {
        return workoutPlanRepository.findAll();
    }

    public List<WorkoutPlan> getWorkoutPlansByMemberId(Long memberId) {
        return workoutPlanRepository.findByMember_Id(memberId);
    }

    public List<WorkoutPlan> getWorkoutPlansByTrainerId(Long trainerId) {
        return workoutPlanRepository.findByTrainer_Id(trainerId);
    }

    public WorkoutPlan addWorkoutPlan(WorkoutPlan workoutPlan) {
        return workoutPlanRepository.save(workoutPlan);
    }
}