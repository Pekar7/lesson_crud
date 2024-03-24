package com.karen;

import com.karen.controller.LabelController;
import com.karen.controller.PostController;
import com.karen.controller.WriterController;
import com.karen.model.Label;
import com.karen.model.Post;
import com.karen.repo.label.GsonLabelRepositoryImpl;
import com.karen.repo.label.LabelRepository;
import com.karen.repo.post.GsonPostRepositoryImpl;
import com.karen.repo.post.PostRepository;
import com.karen.repo.writer.GsonWriterRepositoryImpl;
import com.karen.repo.writer.WriterRepository;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

//        LabelRepository labelRepository = new GsonLabelRepositoryImpl();
//        LabelController labelController = new LabelController(labelRepository);
//        labelController.createLabel("1", "name");
//        labelController.createLabel("2", "name");
//
//        List<Label> labels = labelController.getAllLabels();
//        for(Label label : labels) {
//            System.out.println(label);
//        }
//
//        PostRepository postRepository = new GsonPostRepositoryImpl();
//        PostController postController = new PostController(postRepository);
//        postController.createPost("1", "title", "content", labels);
//
//        List<Post> posts = postController.getAllLabels();
//
//        WriterRepository writerRepository = new GsonWriterRepositoryImpl();
//        WriterController writerController = new WriterController(writerRepository);
//        writerController.createWriter("1", "firstName", "lastName", posts);

        LabelRepository labelRepository = new GsonLabelRepositoryImpl();
        LabelController labelController = new LabelController(labelRepository);
        labelController.deleteLabelById("1");

    }
}
