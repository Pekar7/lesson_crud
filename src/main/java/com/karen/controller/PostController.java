package com.karen.controller;

import com.karen.model.Label;
import com.karen.model.Post;
import com.karen.model.Status;
import com.karen.repo.post.PostRepository;
import java.util.List;

public class PostController {
    
    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void createPost(String id, String title, String content, List<Label> labels) {
        Post post = Post.builder()
                .id(id)
                .title(title)
                .content(content)
                .labels(labels)
                .status(Status.ACTIVE)
                .build();
        postRepository.save(post);
    }

    public Post getPostById(String id) {
        return postRepository.getById(id);
    }

    public List<Post> getAllPosts() {
        return postRepository.getAll();
    }

    public void updatePost(Post post) {
        postRepository.update(post);
    }

    public void deletePostById(String id) {
        postRepository.deleteById(id);
    }
}
