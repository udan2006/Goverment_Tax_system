package com.example.cwdemo.controller;
import com.example.cwdemo.MainMenu;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    @FXML
    public Button transactionFile;

    @FXML
    public void onCloseClick() {
        System.exit(0);
    }


    public void onImportButtonClick() throws IOException {

        Stage prevStage = (Stage) transactionFile.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(MainMenu.class.getResource("insert-taxFile.fxml"));
        Parent root = fxmlLoader.load();

        prevStage.setScene(new Scene(root, 602, 400));
        prevStage.setTitle("Insert Tax File");
        prevStage.show();
    }
}
