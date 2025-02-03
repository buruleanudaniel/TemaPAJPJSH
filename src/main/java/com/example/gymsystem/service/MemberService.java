package com.example.gymsystem.service;

import com.example.gymsystem.entity.Member;
import com.example.gymsystem.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member addMember(Member member) {
        return memberRepository.save(member);
    }

    public List<Member> getMembersByPlan(String planName) {
        return memberRepository.findByMembership_PlanName(planName);
    }

    public List<Member> getOldMembers(java.time.LocalDate date) {
        return memberRepository.findByRegistrationDateBefore(date);
    }

}