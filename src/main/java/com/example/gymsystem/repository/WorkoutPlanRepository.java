package com.example.gymsystem.repository;

import com.example.gymsystem.entity.WorkoutPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutPlanRepository extends JpaRepository<WorkoutPlan, Long> {

    List<WorkoutPlan> findByMember_Id(Long memberId);

    List<WorkoutPlan> findByTrainer_Id(Long trainerId);
}