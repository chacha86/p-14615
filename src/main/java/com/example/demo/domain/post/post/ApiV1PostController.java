package com.example.demo.domain.post.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ApiV1PostController {

    private final PostService postService;

    @PostMapping("/api/v1/posts")
    @ResponseBody
    public String write(String title, String content) {
        postService.write(title, content);

        return "글 작성이 완료되었습니다.";
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
