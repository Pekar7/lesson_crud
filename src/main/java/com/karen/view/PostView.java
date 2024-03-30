package com.karen.view;

import com.karen.controller.PostController;
import com.karen.model.Label;
import com.karen.model.Post;
import com.karen.model.Status;
import com.karen.repo.label.LabelRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PostView {
    private LabelRepository labelRepository;
    private final PostController postController;
    private final Scanner scanner;

    public PostView(PostController postController) {
        this.postController = postController;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Select optional");
        System.out.println("0 - Exit");
        System.out.println("1 - Create Post");
        System.out.println("2 - Get Post by Id");
        System.out.println("3 - Get All Labels");
        System.out.println("4 - Update Post");
        System.out.println("5 - Delete Post");

        String num = scanner.nextLine();
        String id;
        String title;
        String content;
        List<Label> labels = labelRepository.getAll();
        switch (num) {
            case "0":
                break;
            case "1":
                System.out.println("Create Post id: "); id = scanner.nextLine();
                System.out.println("Create title: "); title = scanner.nextLine();
                System.out.println("Create content: "); content = scanner.nextLine();
                postController.createPost(id, title, content, labels);
                break;
            case "2":
                System.out.println("Get Post with id: "); id = scanner.nextLine();
                postController.getPostById(id);
                break;
            case "3":
                System.out.println("Get all posts ");
                postController.getAllPosts();
                break;
            case "4":
                System.out.println("Create Post id: "); id = scanner.nextLine();
                System.out.println("Create title: "); title = scanner.nextLine();
                System.out.println("Create content: "); content = scanner.nextLine();
                postController.updatePost(new Post(id, title, content, labels, Status.ACTIVE));
                break;
            case "5":
                System.out.println("Delete Post with id: "); id = scanner.nextLine();
                postController.deletePostById(id);
                break;

        }
    }
}
