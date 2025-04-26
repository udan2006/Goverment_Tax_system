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


    private TableView <Transaction> table;

    public void setTable(TableView <Transaction> table) {
        this.table = table;
    }


    private TransactionTableController TransactionController;
    public void setTransactionController(TransactionTableController TransactionController) {
        this.TransactionController = TransactionController;
    }

    private Transaction transaction;
    private Checksum checksum = new Checksum();


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

        try{
            itemCode = itemCodeField.getText();
            internalPrice = Double.parseDouble(internalPriceField.getText());
            discount = Double.parseDouble(discountField.getText());
            salesPrice = Double.parseDouble(salesPriceField.getText());
            quantity = Integer.parseInt(quantityField.getText());
        }catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid number");
            alert.showAndWait();
            return;
        }

        transaction.setItemCode(itemCode);
        transaction.setInternalPrice(internalPrice);
        transaction.setDiscount(discount);
        transaction.setSalesPrice(salesPrice);
        transaction.setQuantity(quantity);

        String combine = transaction.getBillNumber() +
                transaction.getItemCode() +
                transaction.getInternalPrice() +
                transaction.getDiscount() +
                transaction.getSalesPrice() +
                transaction.getQuantity();

        int calculateChecksum = checksum.calculateChecksum(combine);
        transaction.setCheckSum(calculateChecksum);
        boolean hasSpecialChar = !transaction.getItemCode().matches("^[a-zA-Z0-9]*$");
        boolean checksumMissMatch = calculateChecksum != transaction.getCheckSum();
        boolean negativeCheck = transaction.getInternalPrice() < 0;

        if(hasSpecialChar || checksumMissMatch || negativeCheck){
            transaction.setValidationStatus("Invalid");
        }else {
            transaction.setValidationStatus("Valid");
        }

        table.refresh();
        TransactionController.saveUpdatesToCsv();

        Stage stage = (Stage) itemCodeField.getScene().getWindow();
        stage.close();
    }

    public void cancelButtonClick(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
