package com.example.gymsystem.service;

import com.example.gymsystem.entity.Member;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StreamService {

    public List<String> getActiveMembers(List<Member> members) {
        return members.stream()
                .filter(member -> member.getMembership() != null)
                .map(Member::getEmail)
                .collect(Collectors.toList());
    }
}