package com.irinayanushkevich.crud_2.controller;

import com.irinayanushkevich.crud_2.model.Label;
import com.irinayanushkevich.crud_2.model.Post;
import com.irinayanushkevich.crud_2.service.PostService;

import java.util.List;

public class PostController {
    private final PostService postService = new PostService();

    public Post create(String content, List<Label> labels) {
        Post post = new Post(null, content, labels);
        return postService.create(post);
    }

    public Post getById(Long id) {
        return postService.getById(id);
    }

    public Post edit(Long id, String content, List<Label> labels) {
        Post post = new Post(id, content, labels);
        return postService.edit(post);
    }

    public boolean delete(Long id) {
        return postService.delete(id);
    }

    public List<Post> getAll() {
        return postService.getAll();
    }
}