package com.university;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/** Main JavaFX application and five-second scheduled heartbeat. */
public class App extends Application {
    private ScheduledExecutorService scheduler;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(
                getClass().getResource("/com/university/MainView.fxml"));

        primaryStage.setTitle("University Management System");
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();

        scheduler = Executors.newSingleThreadScheduledExecutor(runnable -> {
            Thread thread = new Thread(runnable, "system-heartbeat");
            thread.setDaemon(true);
            return thread;
        });

        Runnable heartbeat = () ->
                System.out.println("System Heartbeat: Checking for updates at " + new Date());

        scheduler.scheduleAtFixedRate(heartbeat, 0, 5, TimeUnit.SECONDS);
    }

    @Override
    public void stop() {
        if (scheduler != null) {
            scheduler.shutdownNow();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
