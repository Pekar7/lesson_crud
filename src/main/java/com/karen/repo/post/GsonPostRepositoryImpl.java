package com.karen.repo.post;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.karen.model.Label;
import com.karen.model.Post;
import com.karen.model.Status;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class GsonPostRepositoryImpl implements PostRepository {
    private final String fileName = "src/main/resources/posts.json";
    private final Gson gson = new Gson();

    @Override
    public Post getById(String id) {
        return getAll().stream()
                .filter(post -> post.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Post> getAll() {
        try (Reader reader = new FileReader(fileName)) {
            return gson.fromJson(reader, new TypeToken<List<Post>>() {}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isIdExist(String id) {
        List<Post> posts = getAll();
        if (posts != null) {
            return posts.stream().map(Post::getId).anyMatch(labelId -> labelId.equals(id));
        }
        return false;
    }

    @Override
    public void save(Post entity) {
        List<Post> posts = getAll();
        if (posts == null || !isIdExist(entity.getId())) {
            if (posts == null) {
                posts = new ArrayList<>();
            }
            posts.add(entity);
            writeToFile(posts);
        } else {
            throw new IllegalArgumentException("Id already exists: " + entity.getId());
        }
    }

    @Override
    public void update(Post entity) {
        List<Post> posts = getAll();
        for (int i = 0; i < posts.size(); i++) {
            if (posts.get(i).getId().equals(entity.getId())) {
                posts.set(i, entity);
                writeToFile(posts);
                return;
            }
        }
    }

    @Override
    public void deleteById(String id) {
        List<Post> posts = getAll();
        for (Post post : posts) {
            if (post.getId().equals(id)) {
                post.setStatus(Status.DELETED);
                break;
            }
            else {
                throw new IllegalArgumentException("This id doesn't exit " + id);
            }
        }
        writeToFile(posts);
    }

    private void writeToFile(List<Post> posts) {
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(posts, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

