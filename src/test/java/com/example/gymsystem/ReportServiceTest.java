package com.example.gymsystem;

import com.example.gymsystem.entity.Member;
import com.example.gymsystem.entity.Membership;
import com.example.gymsystem.repository.MemberRepository;
import com.example.gymsystem.repository.MembershipRepository;
import com.example.gymsystem.service.ReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.Duration;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ReportServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MembershipRepository membershipRepository;

    private ReportService reportService;

    @BeforeEach
    void setUp() {

        // salvare abonamente
        Membership basic = new Membership("Basic", 50.0, java.time.Duration.ofDays(30));
        basic = membershipRepository.save(basic);

        Membership premium = new Membership("Premium", 100.0, java.time.Duration.ofDays(90));
        premium = membershipRepository.save(premium);

        // salvare clienti
        memberRepository.save(new Member("Alice", "alice@example.com", basic));
        memberRepository.save(new Member("Bob", "bob@example.com", premium));
        memberRepository.save(new Member("Charlie", "charlie@example.com", basic));

        // adaugare client fara abonament
        memberRepository.save(new Member("Diana", "diana@example.com", null)); // No membership

        // initializare report service
        reportService = new ReportService(memberRepository);
    }

    @Test
    void testGenerateMembershipReport() {
        Map<String, Long> report = reportService.generateMembershipReport();
        assertNotNull(report);
        assertFalse(report.isEmpty(), "Report should not be empty");

        // Check the counts for each membership plan
        assertEquals(3, report.size(), "Expected three membership plans: Basic, Premium, and No Membership");
        assertEquals(2L, report.get("Basic"), "Basic plan should have 2 members");
        assertEquals(1L, report.get("Premium"), "Premium plan should have 1 member");
        assertEquals(1L, report.get("No Membership"), "No Membership should have 1 member");
    }
}