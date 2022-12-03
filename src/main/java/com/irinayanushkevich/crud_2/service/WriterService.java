package com.irinayanushkevich.crud_2.service;

import com.irinayanushkevich.crud_2.model.Writer;
import com.irinayanushkevich.crud_2.repository.WriterRepository;
import com.irinayanushkevich.crud_2.repository.jdbc_rep.JdbcWriterRepositoryImpl;

import java.util.List;

public class WriterService {
    private final WriterRepository writerRep;

    public WriterService() {
        writerRep = new JdbcWriterRepositoryImpl();
    }

    public WriterService(WriterRepository writerRep) {
        this.writerRep = writerRep;
    }

    public Writer create(Writer writer) {
        return writerRep.create(writer);
    }

    public Writer getById(Long id) {
        return writerRep.getById(id);
    }

    public Writer edit(Writer writer) {
        return writerRep.edit(writer);
    }

    public boolean delete(Long id) {
        return writerRep.delete(id);
    }

    public List<Writer> getAll() {
        return writerRep.getAll();
    }
}
