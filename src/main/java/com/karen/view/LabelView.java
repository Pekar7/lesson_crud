package com.karen.view;

import com.karen.controller.LabelController;
import com.karen.model.Label;
import com.karen.model.Status;

import java.util.Scanner;

public class LabelView {
    private final LabelController labelController;
    private final Scanner scanner;

    public LabelView(LabelController labelController) {
        this.labelController = labelController;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Select optional");
        System.out.println("0 - Exit");
        System.out.println("1 - Create Label");
        System.out.println("2 - Get Label by Id");
        System.out.println("3 - Get All Labels");
        System.out.println("4 - Update Label");
        System.out.println("5 - Delete Label");

        String num = scanner.nextLine();
        String id;
        String name;
        switch (num) {
            case "0":
                break;
            case "1":
                System.out.println("Create label id: ");
                id = scanner.nextLine();
                System.out.println("Create label Name: ");
                name = scanner.nextLine();
                labelController.createLabel(id, name);
                break;
            case "2":
                System.out.println("Get label with id: ");
                id = scanner.nextLine();
                System.out.println(labelController.getLabelById(id));
                break;
            case "3":
                System.out.println("Get all labels ");
                System.out.println(labelController.getAllLabels());
                break;
            case "4":
                System.out.println("Update label with id: ");
                id = scanner.nextLine();
                System.out.println("Update label Name: ");
                name = scanner.nextLine();
                labelController.updateLabel(new Label(id, name, Status.ACTIVE));
                break;
            case "5":
                System.out.println("Delete label with id: ");
                id = scanner.nextLine();
                labelController.deleteLabelById(id);
                break;
        }
    }
}
