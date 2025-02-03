package com.example.gymsystem.service;

import com.example.gymsystem.entity.Member;
import com.example.gymsystem.entity.Membership;
import com.example.gymsystem.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class ReportService {

    private final MemberRepository memberRepository;

    @Autowired
    public ReportService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Map<String, Long> generateMembershipReport() {
        List<Member> members = memberRepository.findAll();

        return members.stream()
                .filter(member -> member.getMembership() != null)
                .collect(Collectors.groupingBy(
                        member -> member.getMembership().getPlanName(),
                        Collectors.counting()
                ));
    }
}