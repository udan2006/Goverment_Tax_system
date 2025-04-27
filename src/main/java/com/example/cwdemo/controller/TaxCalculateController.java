package com.example.cwdemo.controller;

import com.example.cwdemo.model.Transaction;
import com.example.cwdemo.service.CalculateTax;
import javafx.collections.ObservableList;
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

    // ObservableList to store the transaction List
    private ObservableList<Transaction> transactions;
    private CalculateTax calculateTax = new CalculateTax();

    public void setTransactions(ObservableList<Transaction> transactions) {
        this.transactions = transactions;
    }

    // set the total profit and total loss values in the controller and updates the labels
    public void setText(double totalProfit, double totalLoss) {
        this.totalProfitCol = totalProfit;
        this.totalLossCol = totalLoss;

        totalProfitLabel.setText(String.valueOf(totalProfit));
        totalLossLabel.setText(String.valueOf(totalLoss));
    }

    // handle the calculating total tax using the calculateTax service
    public void onCalculateButton(ActionEvent event) {
        try {
            double taxRate = Double.parseDouble(enterTaxArea.getText());
            double finalTax = calculateTax.calculateTotalTax(transactions, taxRate);

            finalTaxLabel.setText(String.valueOf(finalTax));
        }catch (NumberFormatException e) {
            // Enter the invalid input get a alert
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Please enter a valid number");
        alert.showAndWait();
        }
    }

    // when Click done button close the application
    public void ExitCodeButton(ActionEvent event) {
        System.exit(0);
    }
}
