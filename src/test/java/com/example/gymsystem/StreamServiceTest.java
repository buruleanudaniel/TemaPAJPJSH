package com.example.gymsystem;

import com.example.gymsystem.entity.Member;
import com.example.gymsystem.repository.MemberRepository;
import com.example.gymsystem.service.StreamService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Import(DataInitializer.class)
class StreamServiceTest {

    @Autowired
    private StreamService streamService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void testGetActiveMembers() {
        List<Member> members = memberRepository.findAll();

        List<String> activeMemberEmails = streamService.getActiveMembers(members);

        assertEquals(2, activeMemberEmails.size());
        assertEquals(List.of("alice@example.com", "bob@example.com"), activeMemberEmails);
    }
}