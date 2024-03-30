package com.karen.repo.post;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.karen.model.Post;
import com.karen.model.Status;
import com.karen.model.Writer;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Collections;
import java.util.List;

public class GsonPostRepositoryImpl implements PostRepository {
    private final String fileName = "src/main/resources/posts.json";
    private final Gson gson = new Gson();

    @Override
    public Post getById(String id) {
        return getPostsFromFile().stream()
                .filter(post -> post.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Post> getAll() {
        return getPostsFromFile();
    }

    @Override
    public Post save(Post entity) {
        List<Post> posts = getPostsFromFile();
        boolean isLabelExist = posts.stream()
                .map(Post::getId)
                .anyMatch(id -> id.equals(entity.getId()));
        if (!isLabelExist) {
            posts.add(entity);
            sendPostsToFile(posts);
        } else {
            throw new IllegalArgumentException("Post with id " + entity.getId() + " already exist");
        }
        return entity;
    }

    @Override
    public Post update(Post entity) {
        List<Post> posts = getPostsFromFile();
        Post post = posts.stream().filter(p-> p.getId().equals(entity.getId()))
                .findFirst().orElseThrow();
        posts.set(Integer.parseInt(post.getId()), entity);
        sendPostsToFile(posts);
        return entity;
    }

    @Override
    public void deleteById(String id) {
        List<Post> posts = getPostsFromFile();
        Post post = posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst().orElseThrow();
        post.setStatus(Status.DELETED);
        sendPostsToFile(posts);
    }

    private List<Post> getPostsFromFile() {
        try(Reader reader = new FileReader(fileName)) {
            return gson.fromJson(reader, new TypeToken<List<Writer>>() {}.getType());
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    private void sendPostsToFile(List<Post> posts) {
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(posts, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

