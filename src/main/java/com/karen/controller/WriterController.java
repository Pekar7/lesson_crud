package com.karen.controller;

import com.karen.model.Post;
import com.karen.model.Status;
import com.karen.model.Writer;
import com.karen.repo.writer.WriterRepository;
import java.util.List;

public class WriterController {

    private final WriterRepository writerRepository;

    public WriterController(WriterRepository writerRepository) {
        this.writerRepository = writerRepository;
    }

    public void createWriter(String id, String firstName, String lastName, List<Post> posts) {
        Writer writer = Writer.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .posts(posts)
                .status(Status.ACTIVE)
                .build();
        writerRepository.save(writer);
    }

    public Writer getWriterById(String id) {
        return writerRepository.getById(id);
    }

    public List<Writer> getAllWriters() {
        return writerRepository.getAll();
    }

    public void updateWriter(Writer writer) {
        writerRepository.update(writer);
    }

    public void deleteWriterById(String id) {
        writerRepository.deleteById(id);
    }
}
