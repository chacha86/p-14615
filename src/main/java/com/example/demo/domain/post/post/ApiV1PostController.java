package com.example.demo.domain.post.post;

import com.example.demo.domain.member.Member;
import com.example.demo.domain.member.MemberService;
import com.example.demo.global.exception.ServiceException;
import com.example.demo.global.rq.Rq;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    private final Rq rq;

    @Getter
    @NoArgsConstructor
    static class WriteReqBody {
        private String title;
        private String content;
    }


    @PostMapping("/api/v1/posts")
    @ResponseBody
    public RsData write(@RequestBody WriteReqBody writeReqBody) {

        Member member = rq.getActor();
        postService.write(writeReqBody.title, writeReqBody.content);

        RsData rsData = new RsData();
        rsData.setResultCode("200");
        rsData.setMessage(member.getUsername() + "님의 글 작성이 완료되었습니다.");

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
