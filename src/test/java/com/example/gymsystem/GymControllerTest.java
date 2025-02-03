package com.example.gymsystem;

import com.example.gymsystem.controller.GymController;
import com.example.gymsystem.entity.Member;
import com.example.gymsystem.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class GymControllerTest {

    @Mock
    private MemberService memberService;

    @InjectMocks
    private GymController gymController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetMembersWithSpel() {
        Member member1 = new Member("John", "john@example.com", null);
        Member member2 = new Member("Jane", "jane@example.com", null);

        when(memberService.getAllMembers()).thenReturn(Arrays.asList(member1, member2));

        List<String> result = gymController.getMembersWithSpel("name == 'John'");

        assertEquals(1, result.size());
        assertEquals("John", result.get(0));
    }
}