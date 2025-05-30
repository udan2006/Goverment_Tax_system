package com.example.cwdemo.controller;

import com.example.cwdemo.service.Checksum;
import com.example.cwdemo.model.Transaction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PopupEditController {
    @FXML
    public TextField itemCodeField;
    @FXML
    public TextField internalPriceField;
    @FXML
    public TextField discountField;
    @FXML
    public TextField salesPriceField;
    @FXML
    public TextField quantityField;
    public Button cancelButton;


    // Reference to the TableView containing transaction records
    private TableView <Transaction> table;

    // setter method to receive the table instance from the parent controller
    public void setTable(TableView <Transaction> table) {
        this.table = table;
    }


    private TransactionTableController TransactionController;
    // setter methods to receive the TransactionTableController instance
    public void setTransactionController(TransactionTableController TransactionController) {
        this.TransactionController = TransactionController;
    }

    private Transaction transaction;
    private Checksum checksum = new Checksum();


    // when I click edit button it open with the these set values of the row
    public void setRecord(Transaction transaction) {
        this.transaction = transaction;
        itemCodeField.setText(transaction.getItemCode());
        internalPriceField.setText(String.valueOf(transaction.getInternalPrice()));
        discountField.setText(String.valueOf(transaction.getDiscount()));
        salesPriceField.setText(String.valueOf(transaction.getSalesPrice()));
        quantityField.setText(String.valueOf(transaction.getQuantity()));
    }


    public void updateRecords(ActionEvent event) {
        String itemCode;
        Double internalPrice;
        Double discount;
        Double salesPrice;
        Integer quantity;

        // input validation: ensure all numeric fields contain valid number
        try{
            itemCode = itemCodeField.getText();
            internalPrice = Double.parseDouble(internalPriceField.getText());
            discount = Double.parseDouble(discountField.getText());
            salesPrice = Double.parseDouble(salesPriceField.getText());
            quantity = Integer.parseInt(quantityField.getText());

            if(internalPrice <0.0 || discount <0.0 || salesPrice <0.0 ||quantity < 0){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter valid values");
                alert.showAndWait();
                return;
            }

            if(itemCode.isEmpty() || !itemCode.matches("^[a-zA-Z0-9_]*$")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter valid item code only using underscore");
                alert.showAndWait();
                return;
            }
        }catch (NumberFormatException e){
            // show an error alert if numeric input is invalid
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid number");
            alert.showAndWait();
            return;
        }

        //Update the transaction object with new values
        transaction.setItemCode(itemCode);
        transaction.setInternalPrice(internalPrice);
        transaction.setDiscount(discount);
        transaction.setSalesPrice(salesPrice);
        transaction.setQuantity(quantity);

        // Recalculate checksum
        String combine = transaction.getBillNumber() +
                transaction.getItemCode() +
                transaction.getInternalPrice() +
                transaction.getDiscount() +
                transaction.getSalesPrice() +
                transaction.getQuantity();

        int calculateChecksum = checksum.calculateChecksum(combine);
        transaction.setCheckSum(calculateChecksum);
        itemCode = itemCodeField.getText().trim();
        // validation checker
        boolean hasSpecialChar = !itemCode.matches("^[a-zA-Z0-9_]*$");
        boolean checksumMissMatch = calculateChecksum != transaction.getCheckSum();
        boolean negativeCheck = transaction.getInternalPrice() < 0;

        // set validation status based on checks
        if(hasSpecialChar || checksumMissMatch || negativeCheck){
            transaction.setValidationStatus("Invalid");
        }else {
            transaction.setValidationStatus("Valid");
        }

        // refresh the TableView
        table.refresh();
        // save changes to the csv file
        TransactionController.saveUpdatesToCsv();

        // close the popup window
        Stage stage = (Stage) itemCodeField.getScene().getWindow();
        stage.close();
    }

    // close the popup window without saving changes
    public void cancelButtonClick(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
