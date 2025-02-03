package com.example.gymsystem;

import com.example.gymsystem.entity.Member;
import com.example.gymsystem.entity.Membership;
import com.example.gymsystem.repository.MemberRepository;
import com.example.gymsystem.repository.MembershipRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MembershipRepository membershipRepository;

    private Membership membership;

    @BeforeEach
    void setUp() {
        // Save the Membership first
        membership = new Membership("Basic", 50.0, java.time.Duration.ofDays(30));
        membership = membershipRepository.save(membership); // Reassign to capture the persisted entity
        assertNotNull(membership);

        // Save Members with the saved Membership
        memberRepository.save(new Member("John", "john@example.com", membership));
        memberRepository.save(new Member("Jane", "jane@example.com", membership));
    }

    @Test
    void testFindAllMembers() {
        List<Member> members = memberRepository.findAll();
        assertNotNull(members);
        assertEquals(2, members.size());
    }

    @Test
    void testFindByMembership_PlanName() {
        List<Member> members = memberRepository.findByMembership_PlanName("Basic");
        assertNotNull(members);
        assertEquals(2, members.size());
    }

    @Test
    void testFindByRegistrationDateBefore() {
        LocalDate date = LocalDate.now().plusDays(1); // Tomorrow
        List<Member> members = memberRepository.findByRegistrationDateBefore(date);
        assertNotNull(members);
        assertEquals(2, members.size());
    }
}