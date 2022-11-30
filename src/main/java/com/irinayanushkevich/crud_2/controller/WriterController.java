package com.irinayanushkevich.crud_2.controller;

import com.irinayanushkevich.crud_2.model.Post;
import com.irinayanushkevich.crud_2.model.Writer;
import com.irinayanushkevich.crud_2.service.WriterService;

import java.util.List;

public class WriterController {

    private final WriterService writerService = new WriterService();

    public Writer create(String firstName, String secondName, List<Post> posts) {
        Writer writer = new Writer(null, firstName, secondName, posts);
        return writerService.create(writer);
    }

    public Writer getById(Long id) {
        return writerService.getById(id);
    }

    public Writer edit(Long id, String firstName, String secondName, List<Post> posts) {
        Writer writer = new Writer(id, firstName, secondName, posts);
        return writerService.edit(writer);
    }

    public boolean delete(Long id) {
        return writerService.delete(id);
    }

    public List<Writer> getAll() {
        return writerService.getAll();
    }
}

