package com.example.gymsystem;

import com.example.gymsystem.service.GymService; // Correct import
import com.example.gymsystem.repository.GymRepository; // Correct import
import com.example.gymsystem.entity.Member; // Correct import

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class GymSystemTests {

    @MockBean
    private GymRepository gymRepository;

    @Autowired
    private GymService gymService;

    @Test
    void testFindAllMembers() {
        when(gymRepository.findAll()).thenReturn(List.of(new Member()));
        List<Member> members = gymService.getAllMembers();
        assertEquals(1, members.size());
    }
}