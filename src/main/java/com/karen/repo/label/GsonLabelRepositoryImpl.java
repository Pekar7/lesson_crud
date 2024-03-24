package com.karen.repo.label;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.karen.model.Label;
import com.karen.model.Post;
import com.karen.model.Status;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GsonLabelRepositoryImpl implements LabelRepository {
    private final String fileName = "src/main/resources/labels.json";
    private final Gson gson = new Gson();

    @Override
    public Label getById(String id) {
        return getAll().stream()
                .filter(post -> post.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Label> getAll() {
        try (Reader reader = new FileReader(fileName)) {
            return gson.fromJson(reader, new TypeToken<List<Label>>() {}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isIdExist(String id) {
        List<Label> labels = getAll();
        if (labels != null) {
            return labels.stream().map(Label::getId).anyMatch(labelId -> labelId.equals(id));
        }
        return false;
    }

    @Override
    public void save(Label entity) {
        List<Label> labels = getAll();
        if (labels == null) {
            labels = new ArrayList<>();
        }
        if (!isIdExist(entity.getId())) {
            labels.add(entity);
            writeToFile(labels);
        } else {
            throw new IllegalArgumentException("Id already exist: " + entity.getId());
        }
    }

    @Override
    public void update(Label entity) {
        List<Label> labels = getAll();
        for (int i = 0; i < labels.size(); i++) {
            if (labels.get(i).getId().equals(entity.getId())) {
                labels.set(i, entity);
                writeToFile(labels);
                return;
            }
        }
    }

    @Override
    public void deleteById(String id) {
        List<Label> labels = getAll();
        for (Label label : labels) {
            if (label.getId().equals(id)) {
                label.setStatus(Status.DELETED);
                break;
            }
            else {
                throw new IllegalArgumentException("This id doesn't exit " + id);
            }
        }
        writeToFile(labels);
    }

    private void writeToFile(List<Label> labels) {
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(labels, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

