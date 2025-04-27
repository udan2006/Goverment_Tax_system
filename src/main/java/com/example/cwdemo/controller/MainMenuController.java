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


    // when I click close button the terminate the program
    public void onCloseClick() {
        System.exit(0);
    }



    public void onImportButtonClick() throws IOException {
        // get a reference to the current window
        Stage prevStage = (Stage) transactionFile.getScene().getWindow();

        // load the insert Tax file FXML layout
        FXMLLoader fxmlLoader = new FXMLLoader(MainMenu.class.getResource("insert-taxFile.fxml"));
        Parent root = fxmlLoader.load();

        // set the new scene in to the current stage
        prevStage.setScene(new Scene(root, 602, 400));
        prevStage.setTitle("Insert Tax File");
        // show the update stage
        prevStage.show();
    }
}
