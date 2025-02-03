package com.example.gymsystem.service;

import com.example.gymsystem.entity.Member;
import com.example.gymsystem.entity.Membership;
import com.example.gymsystem.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class ReportService {

    private final MemberRepository memberRepository;

    @Autowired
    public ReportService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * Generates a membership report that groups members by their membership plan names
     * and counts the number of members for each plan.
     *
     * @return A map where the key is the membership plan name and the value is the count of members.
     */
    public Map<String, Long> generateMembershipReport() {
        // Fetch all members from the database
        List<Member> members = memberRepository.findAll();

        // Use streams to group members by membership plan name and count them
        return members.stream()
                .filter(member -> member.getMembership() != null) // Exclude members without a membership
                .collect(Collectors.groupingBy(
                        member -> Optional.ofNullable(member.getMembership())
                                .map(Membership::getPlanName)
                                .orElse("No Membership"), // Handle null plan names gracefully
                        Collectors.counting()
                ));
    }
}