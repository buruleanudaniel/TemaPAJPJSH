package com.example.gymsystem.service;

import com.example.gymsystem.entity.Member;
import com.example.gymsystem.repository.GymRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GymService {
    private final GymRepository gymRepository;

    @Autowired
    public GymService(GymRepository gymRepository) {
        this.gymRepository = gymRepository;
    }

    public List<Member> getAllMembers() {
        return gymRepository.findAll();
    }
}