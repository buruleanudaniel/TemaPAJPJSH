package com.example.gymsystem.repository;

import com.example.gymsystem.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    // Custom query methods can be added here if needed
    List<Member> findByMembership_PlanName(String planName);

    List<Member> findByRegistrationDateBefore(java.time.LocalDate date);
}