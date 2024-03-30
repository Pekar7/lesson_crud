package com.karen.repo.label;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.karen.model.Label;
import com.karen.model.Status;

import java.io.*;
import java.util.*;

public class GsonLabelRepositoryImpl implements LabelRepository {
    private final String fileName = "src/main/resources/labels.json";
    private final Gson gson = new Gson();

    @Override
    public Label getById(String id) {
        return getLabelsFromFile().stream()
                .filter(post -> post.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Label> getAll() {
        return getLabelsFromFile();
    }

    @Override
    public Label save(Label entity) {
        List<Label> labels = getLabelsFromFile();

        boolean isLabelExist = labels.stream()
                .anyMatch(label -> label.getId().equals(entity.getId()));

        if (!isLabelExist) {
            labels.add(entity);
            sendLabelsToFile(labels);
        } else {
            throw new IllegalArgumentException("Label with id " + entity.getId() + " already exists");
        }

        return entity;
    }

    @Override
    public Label update(Label entity) {
        List<Label> labels = getLabelsFromFile();
        Label label = labels.stream().filter(l-> l.getId().equals(entity.getId()))
                .findFirst().orElseThrow();
        labels.set(Integer.parseInt(label.getId()), entity);
        sendLabelsToFile(labels);
        return entity;
    }

    @Override
    public void deleteById(String id) {
        List<Label> labels = getLabelsFromFile();
        Label label = labels.stream()
                .filter(l -> l.getId().equals(id))
                .findFirst().orElseThrow();
        label.setStatus(Status.DELETED);
        sendLabelsToFile(labels);
    }

    private List<Label> getLabelsFromFile() {
        try(Reader reader = new FileReader(fileName)) {
            return gson.fromJson(reader, new TypeToken<List<Label>>() {}.getType());
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    private void sendLabelsToFile(List<Label> labels) {
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(labels, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

