package com.example.cwdemo.controller;

import com.example.cwdemo.model.Transaction;
import com.example.cwdemo.service.CalculateTax;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TaxCalculateController {
    @FXML
    public TextField enterTaxArea;
    @FXML
    public Label totalProfitLabel;
    @FXML
    public Label totalLossLabel;
    @FXML
    public Label finalTaxLabel;


    private double totalProfitCol;
    private double totalLossCol;

    public void setText(double totalProfit, double totalLoss) {
        this.totalProfitCol = totalProfit;
        this.totalLossCol = totalLoss;

        totalProfitLabel.setText(String.valueOf(totalProfit));
        totalLossLabel.setText(String.valueOf(totalLoss));
    }

    public void onCalculateButton(ActionEvent event) {
        try {
            double taxRate = Double.parseDouble(enterTaxArea.getText());
            double net = totalProfitCol + totalLossCol;
            double finalTax = net * (taxRate / 100) ;

            finalTaxLabel.setText(String.valueOf(finalTax));
        }catch (NumberFormatException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Please enter a valid number");
        alert.showAndWait();
        }
    }

    public void ExitCodeButton(ActionEvent event) {
        System.exit(0);
    }
}
