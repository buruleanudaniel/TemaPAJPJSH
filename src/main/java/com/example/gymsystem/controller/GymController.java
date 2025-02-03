package com.example.gymsystem.controller;

import com.example.gymsystem.service.MemberService;
import com.example.gymsystem.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GymController {

    private final MemberService memberService;

    @Autowired
    public GymController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members")
    public List<String> getMembersWithSpel(@RequestParam String filter) {
        ExpressionParser parser = new SpelExpressionParser();
        var expression = parser.parseExpression(filter);

        return memberService.getAllMembers().stream()
                .filter(member -> expression.getValue(member) instanceof Boolean && (Boolean) expression.getValue(member))
                .map(Member::getName)
                .collect(Collectors.toList());
    }
}