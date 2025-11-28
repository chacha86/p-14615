package com.example.demo.domain.post.post;

import com.example.demo.domain.member.Member;
import com.example.demo.domain.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ApiV1PostController {

    private final PostService postService;
    private final MemberService memberService;

    @PostMapping("/api/v1/posts")
    @ResponseBody
    public RsData write(String title, String content, String username, String password) {

        // 우리 회원이 맞는지 체크
        Optional<Member> opMember = memberService.findByUsername(username);
        if(opMember.isEmpty()) {
//            return "없는 회원입니다."; // 인증 실패 - 401

            RsData rsData = new RsData();
            rsData.setResultCode("401");
            rsData.setMessage("없는 회원입니다.");

            return rsData;
        }

        Member member = opMember.get();

        // 우리 회원이 맞다면 정말 그 회원이 맞는지 체크
        if(!member.getPassword().equals(password)) {
//            return "비밀번호가 틀렸습니다."; // 401

            RsData rsData = new RsData();
            rsData.setResultCode("401");
            rsData.setMessage("비밀번호가 틀렸습니다.");

            return rsData;

        }

        postService.write(title, content);

//        return username + "님의 글 작성이 완료되었습니다."; // 200
        RsData rsData = new RsData();
        rsData.setResultCode("200");
        rsData.setMessage(username + "님의 글 작성이 완료되었습니다.");

        return rsData;
    }

    @GetMapping("/api/v1/posts")
    @ResponseBody
    public List<Post> list() {
        List<Post> postList = postService.list();

        return postList;
    }

    @DeleteMapping("/api/v1/posts/{id}")
    @ResponseBody
    public String remove(@PathVariable Long id) {

        postService.delete(id);

        return "글 삭제가 완료되었습니다.";
    }
}
