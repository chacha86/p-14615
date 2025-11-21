package com.example.demo.global.initData;

import com.example.demo.domain.post.post.Post;
import com.example.demo.domain.post.post.PostRepository;
import com.example.demo.domain.post.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BaseInitData {

    private final PostService postService;

    @Bean
    public ApplicationRunner test() {

        return new ApplicationRunner() {
            @Override
            public void run(ApplicationArguments args) throws Exception {

                if(postService.count() > 0) return;

                Post p1 = postService.write("첫번째 게시글", "첫번째 게시글 내용입니다.");
                Post p2 = postService.write("두번째 게시글", "두번째 게시글 내용입니다.");
                Post p3 = postService.write("세번째 게시글", "세번째 게시글 내용입니다.");

            }
        };

    }

}
