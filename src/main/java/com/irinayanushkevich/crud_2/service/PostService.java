package com.irinayanushkevich.crud_2.service;

import com.irinayanushkevich.crud_2.model.Post;
import com.irinayanushkevich.crud_2.repository.PostRepository;
import com.irinayanushkevich.crud_2.repository.jdbc_rep.JdbcPostRepositoryImpl;

import java.util.List;

public class PostService {

    private final PostRepository postRep = new JdbcPostRepositoryImpl();

    public Post create(Post post) {
        return postRep.create(post);
    }

    public Post getById(Long id) {
        return postRep.getById(id);
    }

    public Post edit(Post post) {
        return postRep.edit(post);
    }

    public boolean delete(Long id) {
        return postRep.delete(id);
    }

    public List<Post> getAll() {
        return postRep.getAll();
    }
}
