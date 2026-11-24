package com.university;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.io.IOException;

/** Controls navigation between the dashboard and student management view. */
public class MainController {
    @FXML private StackPane centerPane;

    @FXML
    private void initialize() {
        showDashboard();
    }

    @FXML
    private void showDashboard() {
        Label welcome = new Label("Welcome to the University Dashboard!");
        welcome.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");
        centerPane.getChildren().setAll(welcome);
    }

    @FXML
    private void showStudents() {
        try {
            Parent studentView = FXMLLoader.load(
                    getClass().getResource("/com/university/StudentView.fxml"));
            centerPane.getChildren().setAll(studentView);
        } catch (IOException | RuntimeException e) {
            Label error = new Label("Unable to load Student Management: " + e.getMessage());
            error.setWrapText(true);
            centerPane.getChildren().setAll(error);
            e.printStackTrace();
        }
    }
}
