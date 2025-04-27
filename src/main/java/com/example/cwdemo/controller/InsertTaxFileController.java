package com.example.cwdemo.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class InsertTaxFileController {

    public TextField filePath;
    public Button viewFile;  // view file button


    private File selectedFile;

    // when I click browse button in the scene then open file chooser
    public void onClickBrowseButton(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open CSV file");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV file", "*.csv"));
        // opening the file dialog
        File file = fileChooser.showOpenDialog(new Stage());

        // if file path is selected, Display its path in the TextField
        if (file != null) {
            filePath.setText(file.getAbsolutePath());
            selectedFile = file;
        }
    }

    // on click view button go to the table scene
    public void onClickViewButton(ActionEvent event) {
        // check if a file is not selected get a alert
        if (selectedFile == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No file Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a file");
            alert.showAndWait();
            return;

        }
        try {
            // Load the transaction table FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cwdemo/transaction-table.fxml"));
            Parent root = loader.load();

            // get the TransactionTableController instance
            TransactionTableController controller = loader.getController();
            controller.clearTable();
            controller.setTransactions(selectedFile);

            // open a new stage with the transaction table scene
            Stage stage = new Stage();
            stage.setTitle("Transaction File");
            stage.setScene(new Scene(root));
            stage.show();

            // close the current stage
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

