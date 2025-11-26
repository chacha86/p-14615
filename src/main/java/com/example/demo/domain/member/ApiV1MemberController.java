package com.example.demo.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ApiV1MemberController {

    private final MemberService memberService;

    @GetMapping("/api/v1/members")
    @ResponseBody
    public List<Member> list() {

        List<Member> members = memberService.list();

        return members;
    }

}
