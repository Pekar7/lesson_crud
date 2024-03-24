package com.karen.repo.writer;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.karen.model.Label;
import com.karen.model.Post;
import com.karen.model.Status;
import com.karen.model.Writer;
import com.karen.repo.writer.WriterRepository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class GsonWriterRepositoryImpl implements WriterRepository {
    private final String fileName = "src/main/resources/writers.json";
    private final Gson gson = new Gson();

    @Override
    public Writer getById(String id) {
        return getAll().stream()
                .filter(post -> post.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Writer> getAll() {
        try (Reader reader = new FileReader(fileName)) {
            return gson.fromJson(reader, new TypeToken<List<Writer>>() {}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isIdExist(String id) {
        List<Writer> writers = getAll();
        if (writers != null) {
            return writers.stream().map(Writer::getId).anyMatch(labelId -> labelId.equals(id));
        }
        return false;
    }

    @Override
    public void save(Writer entity) {
        List<Writer> writers = getAll();
        if (writers == null) {
            writers = new ArrayList<>();
        }
        if (!isIdExist(entity.getId())) {
            writers.add(entity);
            writeToFile(writers);
        } else {
            throw new IllegalArgumentException("Id already exist: " + entity.getId());
        }
    }

    @Override
    public void update(Writer entity) {
        List<Writer> writers = getAll();
        for (int i = 0; i < writers.size(); i++) {
            if (writers.get(i).getId().equals(entity.getId())) {
                writers.set(i, entity);
                writeToFile(writers);
                return;
            }
        }
    }

    @Override
    public void deleteById(String id) {
        List<Writer> writers = getAll();
        for (Writer writer : writers) {
            if (writer.getId().equals(id)) {
                writer.setStatus(Status.DELETED);
                break;
            }
            else {
                throw new IllegalArgumentException("This id doesn't exit " + id);
            }
        }
        writeToFile(writers);
    }

    private void writeToFile(List<Writer> writers) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            gson.toJson(writers, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
