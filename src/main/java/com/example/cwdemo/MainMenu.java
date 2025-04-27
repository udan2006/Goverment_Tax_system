package com.example.cwdemo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

// Entry point of javaFX application.
public class MainMenu extends Application {
    @Override
    public void start (Stage stage) throws IOException {
        // load the FXML file for the main menu UI
        FXMLLoader fxmlLoader = new FXMLLoader(MainMenu.class.getResource("main-menu.fxml"));
        // Create a new scene using the loaded FXML, with specified width and height
        Scene scene = new Scene(fxmlLoader.load(), 602, 400);
        // set the title of the application window
        stage.setTitle("Main Menu");
        //Attach the scene to the stage
        stage.setScene(scene);
        //Display the stage
        stage.show();
    }
    public static void main(String[] args) {
        launch(); // call the start method
    }
}
