package com.example.gymsystem;

import com.example.gymsystem.entity.Member;
import com.example.gymsystem.entity.Membership;
import com.example.gymsystem.repository.MemberRepository;
import com.example.gymsystem.service.ReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ReportServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private ReportService reportService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerateMembershipReport() {
        // Mock data
        Membership basic = new Membership("Basic", 50.0, java.time.Duration.ofDays(30));
        Membership premium = new Membership("Premium", 100.0, java.time.Duration.ofDays(90));

        Member alice = new Member("Alice", "alice@example.com", basic);
        Member bob = new Member("Bob", "bob@example.com", premium);
        Member charlie = new Member("Charlie", "charlie@example.com", basic);
        Member diana = new Member("Diana", "diana@example.com", null);

        when(memberRepository.findAll()).thenReturn(Arrays.asList(alice, bob, charlie, diana));

        // Generate the report
        Map<String, Long> report = reportService.generateMembershipReport();

        // Verify the result
        assertEquals(3, report.size());
        assertEquals(2L, report.get("Basic"));
        assertEquals(1L, report.get("Premium"));
        assertEquals(1L, report.get("No Membership"));
    }
}