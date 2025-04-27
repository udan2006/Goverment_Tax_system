package com.example.cwdemo.service;

import com.example.cwdemo.model.Transaction;
import javafx.collections.ObservableList;

public class CalculateTax {
    // methods to calculate the total profit from the list of transaction
    public double calculateTotalProfit(ObservableList<Transaction> transactions) {
        double totalProfit = 0.0;

        // iterate through each transaction in the observableList
        for (Transaction transaction : transactions) {
            double profit = transaction.getProfit();
            // only add to total profit if profit is greater than 0
            if (profit > 0) {
                totalProfit += profit;
            }
        }
        return totalProfit;
    }

    // method to calculate the total loss from the list of observableList
    public double calculateTotalLoss(ObservableList<Transaction> transactions) {
        double totalLoss = 0.0;
        for (Transaction transaction : transactions) {
            double loss = transaction.getProfit();
            // only add to the totalLoss if the profit is less than 0
            if (loss < 0) {
                totalLoss += loss;
            }
        }
        return totalLoss;
    }

    // method to calculate the total tax based o the total profit and given tax rate
    public double calculateTotalTax(ObservableList<Transaction> transactions, double taxRate) {
        double totalTax = 0.0;
        for (Transaction transaction : transactions) {
            totalTax += transaction.getProfit();

        }
        // calculate Tax
        return (totalTax * taxRate) / 100;
    }
}
