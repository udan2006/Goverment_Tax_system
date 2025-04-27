package com.example.cwdemo.controller;

import com.example.cwdemo.MainMenu;
import com.example.cwdemo.model.Transaction;
import com.example.cwdemo.service.CalculateProfit;
import com.example.cwdemo.service.CalculateTax;
import com.example.cwdemo.service.Checksum;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TransactionTableController {
    @FXML
    private TableView <Transaction> transactionTable;

    public void setTransactions(TableView <Transaction> transactionTable) {
        transactionTable.setItems(transactions);
    }

    @FXML
    public TableColumn <Transaction,String> billNoCol;
    @FXML
    public TableColumn <Transaction,String> itemCodeCol;
    @FXML
    public TableColumn <Transaction,Double>internalPriceCol;
    @FXML
    public TableColumn <Transaction,Double>discountCol;
    @FXML
    public TableColumn <Transaction,Double> salesPriceCol;
    @FXML
    public TableColumn <Transaction,Integer> quantityCol;
    @FXML
    public TableColumn <Transaction,Integer> checksumCol;
    @FXML
    public TableColumn <Transaction,String> validationCol;
    @FXML
    public TableColumn <Transaction,Double> profitCol;

    // observable list for transaction
    private ObservableList<Transaction> transactions = FXCollections.observableArrayList();
    private Checksum checksum = new Checksum(); // checksum calculation
    private File currentFile;
    private CalculateProfit profitCalculator = new CalculateProfit();


    @FXML
    // Initialize the TableView columns
    public void initialize() {
       billNoCol.setCellValueFactory(new PropertyValueFactory<>("billNumber"));
       itemCodeCol.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
       internalPriceCol.setCellValueFactory(new PropertyValueFactory<>("internalPrice"));
       discountCol.setCellValueFactory(new PropertyValueFactory<>("discount"));
       salesPriceCol.setCellValueFactory(new PropertyValueFactory<>("salesPrice"));
       quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
       checksumCol.setCellValueFactory(new PropertyValueFactory<>("checkSum"));
       validationCol.setCellValueFactory(new PropertyValueFactory<>("ValidationStatus"));
       profitCol.setCellValueFactory(new PropertyValueFactory<>("profit"));

        transactionTable.setItems(transactions); // set the table's items to the transaction list
    }

    // method to load transaction from a file into the table
    public void setTransactions(File file ) {
        transactionTable.getItems().clear();
        this.currentFile = file;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            reader.readLine();// Skip the header
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if(data.length == 7){
                    Transaction transaction  = new Transaction(
                            data[0],data[1],
                            Double.parseDouble(data[2]),
                            Double.parseDouble(data[3]),
                            Double.parseDouble(data[4]),
                            Integer.parseInt(data[5]),
                            Integer.parseInt(data[6])
                    );
                    // add each transaction to the list
                    transactions.add(transaction);
                }
            }
        }catch (IOException e ){
            e.printStackTrace();
        }
    }

    // clean all transaction from the table
    public void clearTable(){
        transactionTable.getItems().clear();
    }

    // validate transaction and show validation summary
    public void validateTransaction(ActionEvent event) {
        int total = transactionTable.getItems().size();
        int validateCount = 0;
        int invalidCount = 0;

        for(Transaction transaction : transactions){
            String combine = transaction.getBillNumber() +
                             transaction.getItemCode() +
                            String.valueOf (transaction.getInternalPrice()) +
                            String.valueOf( transaction.getDiscount()) +
                            String.valueOf(transaction.getSalesPrice()) +
                            String.valueOf(transaction.getQuantity());

            // get checksum for each row
            int calculateChecksum = checksum.calculateChecksum(combine);
            // special character
            boolean hasSpecialChar = !transaction.getItemCode().matches("^[a-zA-Z0-9]*$");
            boolean checksumMissMatch = calculateChecksum != transaction.getCheckSum();
            // negative prices for internal price
            boolean negativeCheck = transaction.getInternalPrice() < 0;

            if(hasSpecialChar || checksumMissMatch || negativeCheck){
                transaction.setValidationStatus("Invalid");
                invalidCount++;
            }else {
                transaction.setValidationStatus("Valid");
                validateCount++;
            }
        }
        // refresh the table after validation
        transactionTable.refresh();

        // alert with validation summary
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Validation Summary");
        alert.setHeaderText("Validation Successful");
        alert.setContentText("Total Transactions: " + total + "\n"
        + "Total Validated Transactions: " + validateCount + "\n"
        + "Total Invalidated Transactions: " + invalidCount + "\n");
        alert.showAndWait();
    }

    // Edit selected transaction
    public void editTransaction(ActionEvent event) {
        Transaction selectedItem = (Transaction) transactionTable.getSelectionModel().getSelectedItem();

        if(selectedItem == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No selection");
            alert.setHeaderText(null);
            alert.setContentText("Please selected a record first");
            alert.showAndWait();

        } else if (selectedItem.getValidationStatus().equals("Valid")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("The record is already valid");
            alert.setHeaderText(null);
            alert.setContentText("Please select a InValid Transaction");
            alert.showAndWait();
        }
        else {
            try {
                // load the popup editor to edit the selection transaction
                FXMLLoader loader = new FXMLLoader(MainMenu.class.getResource("popup-edit.fxml"));
                Parent root = loader.load();

                PopupEditController controller = loader.getController();
                controller.setRecord(selectedItem);
                controller.setTable(transactionTable);
                controller.setTransactionController(this);

                Stage stage = new Stage();
                stage.setTitle("Edit Transaction");
                stage.setScene(new Scene(root));
                stage.show();
            }catch (IOException e ){
                e.printStackTrace();
            }
        }
    }

    // delete invalid records from the table
    public void deleteInvalidRecordsButton(ActionEvent event) {
        Transaction selectedItem = (Transaction) transactionTable.getSelectionModel().getSelectedItem();

        if(selectedItem != null){
            if(selectedItem.getValidationStatus().equals("Invalid")){
                transactions.remove(selectedItem);  // remove invalid transaction
                transactionTable.refresh();
                saveUpdatesToCsv();  // save changes to the file

                // show success alert
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Invalid Record detected");
                alert.setHeaderText(null);
                alert.setContentText("Invalid Record deleted successfully");
                alert.showAndWait();
            }else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Cannot be deleted");
                alert.setHeaderText(null);
                alert.setContentText("It is not a Invalid record");
                alert.showAndWait();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select a record first");
            alert.showAndWait();
        }
    }

    // save updated transactions back to the csv file
    public void saveUpdatesToCsv(){
        if(currentFile == null){
            return;
        }
        try (PrintWriter writer = new PrintWriter(currentFile)){
            // write the header and transaction data in to the csv file
            writer.println("BillNumber, ItemCode, InternalPrice, Discount, SalesPrice, Quantity, Checksum");

            for(Transaction transaction : transactions){
                writer.println(transaction.getBillNumber() + "," +
                        transaction.getItemCode() + "," +
                        transaction.getInternalPrice() + "," +
                        transaction.getDiscount() + "," +
                        transaction.getSalesPrice() + "," +
                        transaction.getQuantity() + "," +
                        transaction.getCheckSum());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    // calculate profit of all transaction
    public void profitCalculationButton(ActionEvent event) {
        for(Transaction transaction : transactions){
            double profit = profitCalculator.calculate(transaction); // set the calculated profit for each transaction
            transaction.setProfit(profit);
        }
        // refresh the table to display updated profits
        transactionTable.refresh();

        // show success alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Profit Calculation");
        alert.setHeaderText(null);
        alert.setContentText("Profit calculation Successfully for each record");
        alert.showAndWait();
    }

    // delete transaction with zero profit
    public void deleteZeroProfit(ActionEvent event) {
        int zeroProfit = 0;

        Iterator<Transaction> iterator = transactions.iterator();
        while(iterator.hasNext()){
            Transaction transaction = iterator.next();
            if(transaction.getProfit() == 0.0){
                iterator.remove(); // remove zero profit transaction
                zeroProfit++;
            }
        }
        saveUpdatesToCsv();  // save changes to the file
        transactionTable.refresh(); // refresh page

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Zero Profit Calculation");
        alert.setHeaderText(null);
        alert.setContentText(zeroProfit + "Zero profit records deleted successfully");
    }

    // open tax rate dialog and calculate tax
    public void calculateTax(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(MainMenu.class.getResource("tax-rate.fxml"));
            Parent root = loader.load();

            TaxCalculateController controller = loader.getController();
            CalculateTax calculateTax = new CalculateTax();

            // calculate total profit and loss
            double totalProfit = calculateTax.calculateTotalProfit(transactions);
            double totalLoss = calculateTax.calculateTotalLoss(transactions);
            controller.setTransactions(transactionTable.getItems());

            // set the calculated tax value in the controller
            controller.setText(totalProfit, totalLoss);

            // show the tax calculation dialog
            Stage stage = new Stage();
            stage.setTitle("Tax Rate");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}