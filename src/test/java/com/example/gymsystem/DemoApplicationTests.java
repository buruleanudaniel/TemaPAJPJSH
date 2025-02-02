package com.example.gymsystem;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.gymsystem.service.GymService;

import java.util.List;

import com.example.gymsystem.entity.Member;
import com.example.gymsystem.repository.GymRepository;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class GymServiceTest {
	@Test
	void testFindAllMembers() {
		GymRepository gymRepository = Mockito.mock(GymRepository.class);
		when(gymRepository.findAll()).thenReturn(List.of(new Member()));

		GymService gymService = new GymService(gymRepository);
		List<Member> members = gymService.getAllMembers();

		assertEquals(1, members.size());
	}
}