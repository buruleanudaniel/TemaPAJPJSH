package com.example.gymsystem;

import com.example.gymsystem.entity.Member;
import com.example.gymsystem.repository.MemberRepository;
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

class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllMembers() {
        Member member1 = new Member("John", "john@example.com", null);
        Member member2 = new Member("Jane", "jane@example.com", null);

        when(memberRepository.findAll()).thenReturn(Arrays.asList(member1, member2));

        List<Member> result = memberService.getAllMembers();

        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getName());
        assertEquals("jane@example.com", result.get(1).getEmail());
    }
}