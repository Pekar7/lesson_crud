package com.karen;

import com.karen.controller.LabelController;
import com.karen.controller.PostController;
import com.karen.controller.WriterController;
import com.karen.repo.label.GsonLabelRepositoryImpl;
import com.karen.repo.label.LabelRepository;
import com.karen.repo.post.GsonPostRepositoryImpl;
import com.karen.repo.post.PostRepository;
import com.karen.repo.writer.GsonWriterRepositoryImpl;
import com.karen.repo.writer.WriterRepository;
import com.karen.view.LabelView;
import com.karen.view.PostView;
import com.karen.view.WriterView;

public class AppContext {
    public void labelStart() {
        LabelRepository labelRepository = new GsonLabelRepositoryImpl();
        LabelController labelController = new LabelController(labelRepository);
        LabelView writerView = new LabelView(labelController);
        writerView.start();
    }

    public void postStart() {
        PostRepository postRepository = new GsonPostRepositoryImpl();
        PostController postController = new PostController(postRepository);
        PostView postView = new PostView(postController);
        postView.start();
    }

    public void writerStart() {
        WriterRepository writerRepository = new GsonWriterRepositoryImpl();
        WriterController writerController = new WriterController(writerRepository);
        WriterView writerView = new WriterView(writerController);
        writerView.start();
    }
}
