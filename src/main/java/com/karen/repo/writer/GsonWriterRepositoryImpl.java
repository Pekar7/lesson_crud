package com.karen.repo.writer;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.karen.model.Status;
import com.karen.model.Writer;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Collections;
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
        return getWritersFromFile();
    }

    @Override
    public Writer save(Writer entity) {
        List<Writer> writers = getWritersFromFile();
        boolean isLabelExist = writers.stream()
                .map(Writer::getId)
                .anyMatch(id -> id.equals(entity.getId()));
        if (!isLabelExist) {
            writers.add(entity);
            sendWritersToFile(writers);
        } else {
            throw new IllegalArgumentException("Writer with id " + entity.getId() + " already exist");
        }
        return entity;
    }

    @Override
    public Writer update(Writer entity) {
        List<Writer> writers = getWritersFromFile();
        Writer Writer = writers.stream().filter(w-> w.getId().equals(entity.getId()))
                .findFirst().orElseThrow();
        writers.set(Integer.parseInt(Writer.getId()), entity);
        sendWritersToFile(writers);
        return entity;
    }

    @Override
    public void deleteById(String id) {
        List<Writer> writers = getWritersFromFile();
        Writer Writer = writers.stream()
                .filter(w -> w.getId().equals(id))
                .findFirst().orElseThrow();
        Writer.setStatus(Status.DELETED);
        sendWritersToFile(writers);
    }

    private List<Writer> getWritersFromFile() {
        try(Reader reader = new FileReader(fileName)) {
            return gson.fromJson(reader, new TypeToken<List<Writer>>() {}.getType());
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    private void sendWritersToFile(List<Writer> writers) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            gson.toJson(writers, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
