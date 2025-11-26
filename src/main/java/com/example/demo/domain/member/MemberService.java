package com.example.demo.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public List<Member> list() {
        return memberRepository.findAll();
    }

    public void join(String name) {
        Member member = new Member();
        member.setNickname(name);
        memberRepository.save(member);
    }
}
