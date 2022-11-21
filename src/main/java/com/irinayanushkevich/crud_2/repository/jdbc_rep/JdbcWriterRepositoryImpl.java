/*package com.irinayanushkevich.crud_2.repository.jdbc_rep;

import com.irinayanushkevich.crud_2.repository.WriterRepository;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class JDBCWriterRepositoryImpl implements WriterRepository {

    @Override
    public Writer create(Writer writer) {
        List<Writer> writers = readFile();
        writer.setId(generateId(writers));
        writers.add(writer);
        writeFile(writers);
        return writer;
    }

    @Override
    public Writer getById(Long id) {
        List<Writer> writers = readFile();
        return writers.stream().filter(writer -> writer.getId().equals(id)).findFirst().orElse(null);
    }

    public Writer edit(Writer writer) {
        List<Writer> writers = readFile();
        Writer writerAfterEdit = null;
        for (Writer w : writers) {
            if (w.getId().equals(writer.getId())) {
                w.setFirstName(writer.getFirstName());
                w.setLastName(writer.getLastName());
                w.setPosts(writer.getPosts());
                writeFile(writers);
                writerAfterEdit = w;
                break;
            }
        }
        return writerAfterEdit;
    }

    @Override
    public boolean delete(Long id) {
        List<Writer> writers = readFile();
        boolean result = false;
        Writer forDel = null;
        for (Writer w : writers) {
            if (w.getId().equals(id)) {
                forDel = w;
                result = true;
                break;
            }
        }
        writers.remove(forDel);
        writeFile(writers);
        return result;
    }

    @Override
    public List<Writer> getAll() {
        return readFile();
    }

    private List<Writer> readFile() {
        List<Writer> w = new ArrayList<>();
        try (Reader reader = new FileReader(file)) {
            Type type = new TypeToken<ArrayList<Writer>>() {
            }.getType();
            w = new Gson().fromJson(reader, type);
            if (w == null) {
                w = new ArrayList<>();
            }
        } catch (IOException e) {
            System.out.println("File doesn't exist");
        }
        return w;
    }

    private void writeFile(List<Writer> writers) {
        try (Writer writer = new FileWriter(file)) {
            gson.toJson(writers, writer);
        } catch (IOException e) {
            System.out.println("Writing file error: " + e);
        }
    }

    private long generateId(List<Writer> writers) {
        long id = 0;
        Optional<Writer> w = writers.stream().max(Comparator.comparing(Writer::getId));
        if (w.isPresent()) {
            id = w.get().getId() + 1;
        }
        return id;
    }
}
*/