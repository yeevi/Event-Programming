package com.university;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;

/** Handles student screen events and coordinates background database tasks. */
public class StudentController {
    @FXML private TextField regField;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TableView<Student> studentTable;
    @FXML private TableColumn<Student, String> colReg;
    @FXML private TableColumn<Student, String> colFirst;
    @FXML private TableColumn<Student, String> colLast;
    @FXML private Label statusLabel;
    @FXML private Button saveButton;

    private final ObservableList<Student> studentList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colReg.setCellValueFactory(data -> data.getValue().regNumberProperty());
        colFirst.setCellValueFactory(data -> data.getValue().firstNameProperty());
        colLast.setCellValueFactory(data -> data.getValue().lastNameProperty());
        studentTable.setItems(studentList);

        saveButton.disableProperty().bind(
                regField.textProperty().isEmpty()
                        .or(firstNameField.textProperty().isEmpty())
                        .or(lastNameField.textProperty().isEmpty())
        );
        loadStudents();
    }

    @FXML
    private void handleSaveStudent() {
        String reg = regField.getText().trim();
        String first = firstNameField.getText().trim();
        String last = lastNameField.getText().trim();

        if (reg.isEmpty() || first.isEmpty() || last.isEmpty()) {
            statusLabel.setText("Please complete all fields.");
            return;
        }

        saveButton.setDisable(true);
        statusLabel.setText("Saving student...");

        Task<Void> saveTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
                String sql = "INSERT INTO students "
                           + "(registration_number, first_name, last_name, email) "
                           + "VALUES (?, ?, ?, ?)";
                try (Connection conn = DatabaseUtil.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, reg);
                    stmt.setString(2, first);
                    stmt.setString(3, last);
                    stmt.setString(4, reg + "@university.edu");
                    stmt.executeUpdate();
                }
                return null;
            }
        };

        saveTask.setOnSucceeded(event -> {
            statusLabel.setText("Student saved successfully!");
            regField.clear();
            firstNameField.clear();
            lastNameField.clear();
            saveButton.setDisable(false);
            loadStudents();
        });

        saveTask.setOnFailed(event -> {
            Throwable error = saveTask.getException();
            statusLabel.setText("Could not save student: "
                    + (error == null ? "Unknown error" : error.getMessage()));
            saveButton.setDisable(false);
        });

        Thread thread = new Thread(saveTask, "save-student-task");
        thread.setDaemon(true);
        thread.start();
    }

    private void loadStudents() {
        statusLabel.setText("Loading students...");
        LoadStudentsTask task = new LoadStudentsTask();

        task.setOnSucceeded(event -> {
            studentList.setAll(task.getValue());
            statusLabel.setText("Loaded " + studentList.size() + " students from database.");
        });

        task.setOnFailed(event -> {
            Throwable error = task.getException();
            statusLabel.setText("Could not load students: "
                    + (error == null ? "Unknown error" : error.getMessage()));
        });

        Thread thread = new Thread(task, "load-students-task");
        thread.setDaemon(true);
        thread.start();
    }
}
