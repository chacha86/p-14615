package com.example.demo.domain.post.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    public long count() {
        return postRepository.count();
    }

    public Post write(String title, String content) {

        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);

        return postRepository.save(post);
    }

    public List<Post> list() {
        List<Post> posts = postRepository.findAll();

        return posts;
    }

    public void delete(Long id) {
        postRepository.deleteById(id);
    }

}

