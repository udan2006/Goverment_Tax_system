package com.example.cwdemo;

import com.example.cwdemo.model.Transaction;
import com.example.cwdemo.service.CalculateTax;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculateTaxTest {

    @Test
    public void testCalculateTaxTotalProfit() {
        CalculateTax calculateTax = new CalculateTax();
        Transaction tran1 = new Transaction("b001","012",100.0,10.0,300.0,20,24);
        tran1.setProfit(50.0);
        Transaction tran2 = new Transaction("b002","011",150.0,10.0,200.0,25,22);
        tran2.setProfit(200.0);
        Transaction tran3 = new Transaction("b003","021",200.0,10.0,250.0,10,20);
        tran3.setProfit(-40.0);

        ObservableList <Transaction> transactions = FXCollections.observableArrayList(tran1, tran2, tran3);

        double expectedValue = 250.0;
        double actualValue = calculateTax.calculateTotalProfit(transactions);

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testCalculateTaxTotalLoss() {
        CalculateTax calculateTax = new CalculateTax();
        Transaction tran1 = new Transaction("b001","012",100.0,10.0,300.0,20,24);
        tran1.setProfit(50.0);
        Transaction tran2 = new Transaction("b002","011",150.0,10.0,200.0,25,22);
        tran2.setProfit(-200.0);
        Transaction tran3 = new Transaction("b003","021",200.0,10.0,250.0,10,20);
        tran3.setProfit(-20.0);

        ObservableList <Transaction> transactions = FXCollections.observableArrayList(tran1, tran2, tran3);

        double expectedValue = -220.0;
        double actualValue = calculateTax.calculateTotalLoss(transactions);

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testCalculateTaxTotalTax() {
        CalculateTax calculateTax = new CalculateTax();
        Transaction tran1 = new Transaction("b001","012",100.0,10.0,300.0,20,24);
        tran1.setProfit(200.0);
        Transaction tran2 = new Transaction("b002","011",150.0,10.0,200.0,25,22);
        tran2.setProfit(500.0);

        ObservableList <Transaction> transactions = FXCollections.observableArrayList(tran1, tran2);

        double TaxRate = 10.0;

        double expectedValue = ((200.0 + 500.0) * TaxRate) / 100;

        double actualValue = calculateTax.calculateTotalTax(transactions, TaxRate);

        assertEquals(expectedValue, actualValue);
    }
}
