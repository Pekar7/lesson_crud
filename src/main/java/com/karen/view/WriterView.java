package com.karen.view;

import com.karen.controller.PostController;
import com.karen.controller.WriterController;
import com.karen.model.Post;
import com.karen.model.Status;
import com.karen.model.Writer;
import com.karen.repo.post.PostRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WriterView {
    private PostRepository postRepository;
    private final WriterController writerController;
    private final Scanner scanner;

    public WriterView(WriterController writerController) {
        this.writerController = writerController;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Select optional");
        System.out.println("0 - Exit");
        System.out.println("1 - Create Writer");
        System.out.println("2 - Get Writer by Id");
        System.out.println("3 - Get All Writers");
        System.out.println("4 - Update Writer");
        System.out.println("5 - Delete Writer");

        String num = scanner.nextLine();
        String id;
        String name;
        String lastName;
        List<Post> posts = postRepository.getAll();
        switch (num) {
            case "0":
                break;
            case "1":
                System.out.println("Create writer id: "); id = scanner.nextLine();
                System.out.println("Create writer Name: "); name = scanner.nextLine();
                System.out.println("Create writer LastName: "); lastName = scanner.nextLine();
                writerController.createWriter(id, name, lastName, posts);
                break;
            case "2":
                System.out.println("Get Writer with id: "); id = scanner.nextLine();
                writerController.getWriterById(id);
                break;
            case "3":
                System.out.println("Get all writers ");
                writerController.getAllWriters();
                break;
            case "4":
                System.out.println("Create Writer id: "); id = scanner.nextLine();
                System.out.println("Create writer Name: "); name = scanner.nextLine();
                System.out.println("Create writer LastName: "); lastName = scanner.nextLine();
                writerController.updateWriter(new Writer(id, name, lastName, posts, Status.ACTIVE));
                break;
            case "5":
                System.out.println("Delete Post with id: "); id = scanner.nextLine();
                writerController.deleteWriterById(id);
                break;
        }
    }
}
