package com.karen.controller;

import com.karen.model.Label;
import com.karen.model.Status;
import com.karen.repo.label.LabelRepository;
import java.util.List;

public class LabelController {

    private final LabelRepository labelRepository;

    public LabelController(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    public void createLabel(String id, String name) {
        Label label = Label.builder()
                .id(id)
                .name(name)
                .status(Status.ACTIVE)
                .build();
        labelRepository.save(label);
    }

    public Label getLabelById(String id) {
        return labelRepository.getById(id);
    }

    public List<Label> getAllLabels() {
        return labelRepository.getAll();
    }

    public void updateLabel(Label label) {
        labelRepository.update(label);
    }

    public void deleteLabelById(String id) {
        labelRepository.deleteById(id);
    }
}
