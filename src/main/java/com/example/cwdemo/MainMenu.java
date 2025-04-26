package com.example.cwdemo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenu extends Application {
    @Override
    public void start (Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainMenu.class.getResource("main-menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 602, 400);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
